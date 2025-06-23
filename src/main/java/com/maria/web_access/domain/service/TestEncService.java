package com.maria.web_access.domain.service;

import com.maria.web_access.domain.entity.MagmaEncryption;
import com.maria.web_access.domain.entity.Test_enc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestEncService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Test_enc> getLatestRecords() {
        List<Test_enc> records = jdbcTemplate.query(
                "SELECT * FROM test_enc ORDER BY id DESC LIMIT 100",
                (rs, rowNum) -> {
                    Test_enc entity = new Test_enc();
                    entity.setId(rs.getLong("id"));
                    entity.setComment(rs.getString("comment"));
                    // Продолжайте получать значения других полей из результирующего набора
                    return entity;
                });

        return records;
    }

    public List<Test_enc> decryptAllData() {
        List<Test_enc> encryptedData = getLatestRecords();
        return encryptedData.stream()
                .peek(data -> data.setComment(decrypt(data.getComment())))
                .collect(Collectors.toList());
    }

    private String decrypt(String encryptedText) {
        byte[] testKey = {
                (byte) 0xff, (byte) 0xfe, (byte) 0xfd, (byte) 0xfc,
                (byte) 0xfb, (byte) 0xfa, (byte) 0xf9, (byte) 0xf8,
                (byte) 0xf7, (byte) 0xf6, (byte) 0xf5, (byte) 0xf4,
                (byte) 0xf3, (byte) 0xf2, (byte) 0xf1, (byte) 0xf0,
                (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33,
                (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77,
                (byte) 0x88, (byte) 0x99, (byte) 0xaa, (byte) 0xbb,
                (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff
        };

        int inputLength = 8;
        int blockSize = 8;
        int numBlocks = (int) Math.ceil((double) inputLength / blockSize);

        byte[] buffer = new byte[inputLength + blockSize - (inputLength % blockSize)];
        byte[] outBlk = new byte[inputLength + blockSize - (inputLength % blockSize)];

        for (int i = 0; i < inputLength; i++) {
            buffer[i] = (byte) encryptedText.charAt(i);
        }

        if (inputLength % blockSize != 0) {
            for (int i = inputLength; i < numBlocks * blockSize; i++) {
                buffer[i] = 0;
            }
        }

        MagmaEncryption.GOST_Magma_Expand_Key(testKey);

        String hexEncodedMessage = encryptedText;
        byte[] hexDecodedBytes = hexStringToByteArray(hexEncodedMessage);

        for (int i = 0; i < numBlocks; i++) {
            byte[] block = new byte[blockSize];
            byte[] outBlock = new byte[blockSize];
            System.arraycopy(hexDecodedBytes, i * blockSize, block, 0, blockSize);
            MagmaEncryption.GOST_Magma_Decrypt(block, outBlock);
            System.arraycopy(outBlock, 0, outBlk, i * blockSize, blockSize);
        }

        return MagmaEncryption.encodeToString(outBlk, inputLength);
    }

    private static byte[] hexStringToByteArray(String hex) {
        String[] hexValues = hex.split(" ");
        byte[] byteArray = new byte[hexValues.length];
        for (int i = 0; i < hexValues.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(hexValues[i], 16);
        }
        return byteArray;
    }
}
