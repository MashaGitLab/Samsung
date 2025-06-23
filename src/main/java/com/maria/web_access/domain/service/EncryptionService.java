package com.maria.web_access.domain.service;

import com.maria.web_access.domain.entity.MagmaEncryption;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    public String encrypt(String input, int inputLength) {
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
        //String input = "T123H56P789G567";
        int blockSize = 8;
        int numBlocks = (int) Math.ceil((double) inputLength / blockSize);
        System.out.print("Num block ");
        System.out.println(blockSize);

        byte[] buffer = new byte[inputLength + blockSize - (inputLength % blockSize)];
        byte[] outBlk = new byte[inputLength + blockSize - (inputLength % blockSize)];

        // Copy input string to buffer
        for (int i = 0; i < inputLength; i++) {
            buffer[i] = (byte) input.charAt(i);
        }

        // Pad the last block with zeros if necessary
        if (inputLength % blockSize != 0) {
            for (int i = inputLength; i < numBlocks * blockSize; i++) {
                buffer[i] = 0;
            }
        }

        MagmaEncryption.GOST_Magma_Expand_Key(testKey);

        System.out.println("Original message:");
        System.out.println(input);

        System.out.println("Bytes before encryption:");
        byteArrayToHexString(buffer, numBlocks * blockSize);

        System.out.println("Encrypting message:");
        for (int i = 0; i < numBlocks; i++) {
            byte[] block = new byte[blockSize];
            byte[] outBlock = new byte[blockSize];
            System.arraycopy(buffer, i * blockSize, block, 0, blockSize);
            MagmaEncryption.GOST_Magma_Encrypt(block, outBlock);
            System.arraycopy(outBlock, 0, outBlk, i * blockSize, blockSize);
        }

        System.out.println("Bytes after encryption:");
        String hexEncodedMessage = byteArrayToHexString(outBlk, numBlocks * blockSize);
        System.out.println(hexEncodedMessage);
        return hexEncodedMessage;
    }



        public String decrypt(String encryptedText, int inputLength) {
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

        int blockSize = 8;
        int numBlocks = (int) Math.ceil((double) inputLength / blockSize);

        byte[] buffer = new byte[inputLength + blockSize - (inputLength % blockSize)];
        byte[] outBlk = new byte[inputLength + blockSize - (inputLength % blockSize)];

            byte[] hexDecodedBytes = hexStringToByteArray(encryptedText);
            System.arraycopy(hexDecodedBytes, 0, buffer, 0, hexDecodedBytes.length);

        if (inputLength % blockSize != 0) {
            for (int i = inputLength; i < numBlocks * blockSize; i++) {
                buffer[i] = 0;
            }
        }

        MagmaEncryption.GOST_Magma_Expand_Key(testKey);

        String hexEncodedMessage = encryptedText;
        //byte[] hexDecodedBytes = hexStringToByteArray(hexEncodedMessage);

        for (int i = 0; i < numBlocks; i++) {
            byte[] block = new byte[blockSize];
            byte[] outBlock = new byte[blockSize];
            System.arraycopy(hexDecodedBytes, i * blockSize, block, 0, blockSize);
            MagmaEncryption.GOST_Magma_Decrypt(block, outBlock);
            System.arraycopy(outBlock, 0, outBlk, i * blockSize, blockSize);
        }
        String itog = MagmaEncryption.encodeToString(outBlk, inputLength);
        itog = itog.replace("_", "");

        return itog;
    }

    private static byte[] hexStringToByteArray(String hex) {
        String[] hexValues = hex.split(" ");
        byte[] byteArray = new byte[hexValues.length];
        for (int i = 0; i < hexValues.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(hexValues[i], 16);
        }
        return byteArray;
    }

    private static String byteArrayToHexString(byte[] array, int length) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            hexString.append(String.format("%02X", array[i]));
            if (i < length - 1) {  // Добавляем пробел, если это не последний элемент
                hexString.append(" ");
            }
        }
        return hexString.toString();
    }
}

