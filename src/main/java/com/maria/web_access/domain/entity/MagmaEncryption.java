package com.maria.web_access.domain.entity;

public class MagmaEncryption {

    private static final byte[][] Pi = {
            {12, 4, 6, 2, 10, 5, 11, 9, 14, 8, 13, 7, 0, 3, 15, 1},
            {6, 8, 2, 3, 9, 10, 5, 12, 1, 14, 4, 7, 11, 13, 0, 15},
            {11, 3, 5, 8, 2, 15, 10, 13, 14, 1, 7, 4, 12, 9, 6, 0},
            {12, 8, 2, 1, 13, 4, 15, 6, 7, 0, 10, 5, 3, 14, 9, 11},
            {7, 15, 5, 10, 8, 1, 6, 13, 0, 9, 3, 14, 11, 4, 2, 12},
            {5, 13, 15, 6, 9, 2, 12, 10, 11, 7, 8, 1, 4, 3, 14, 0},
            {8, 14, 2, 5, 6, 9, 1, 12, 15, 4, 11, 0, 13, 10, 3, 7},
            {1, 7, 14, 13, 0, 5, 8, 3, 4, 15, 10, 6, 9, 12, 11, 2}
    };

    private static byte[][] iterKey = new byte[32][4];

    public static void GOST_Magma_T(byte[] inData, byte[] outData) {
        for (int i = 0; i < 4; i++) {
            byte firstPart = (byte) (inData[i] & 0x0f);
            byte secPart = (byte) ((inData[i] & 0xf0) >> 4);
            firstPart = Pi[i * 2][firstPart];
            secPart = Pi[i * 2 + 1][secPart];
            outData[i] = (byte) ((secPart << 4) | firstPart);
        }
    }

    public static void GOST_Magma_Add(byte[] a, byte[] b, byte[] c) {
        for (int i = 0; i < 4; i++)
            c[i] = (byte) (a[i] ^ b[i]);
    }

    public static void GOST_Magma_Add_32(byte[] a, byte[] b, byte[] c) {
        int internal = 0;
        for (int i = 0; i < 4; i++) {
            internal = (a[i] & 0xFF) + (b[i] & 0xFF) + (internal >> 8);
            c[i] = (byte) (internal & 0xFF);
        }
    }
    public static void GOST_Magma_g(byte[] k, byte[] a, byte[] outData) {
        byte[] internal = new byte[4];
        GOST_Magma_Add_32(a, k, internal);
        GOST_Magma_T(internal, internal);
        int outData32 = (internal[3] & 0xFF);
        outData32 = (outData32 << 8) | (internal[2] & 0xFF);
        outData32 = (outData32 << 8) | (internal[1] & 0xFF);
        outData32 = (outData32 << 8) | (internal[0] & 0xFF);
        outData32 = (outData32 << 11) | (outData32 >>> 21); // Используйте >>> для беззнакового сдвига
        outData[0] = (byte) outData32;
        outData[1] = (byte) (outData32 >>> 8);
        outData[2] = (byte) (outData32 >>> 16);
        outData[3] = (byte) (outData32 >>> 24);
    }
    public static void GOST_Magma_G(byte[] k, byte[] a, byte[] outData) {
        byte[] a0 = new byte[4];
        byte[] a1 = new byte[4];
        byte[] G = new byte[4];
        System.arraycopy(a, 4, a1, 0, 4);
        System.arraycopy(a, 0, a0, 0, 4);
        GOST_Magma_g(k, a0, G);
        GOST_Magma_Add(a1, G, G);
        System.arraycopy(a0, 0, a1, 0, 4);
        System.arraycopy(G, 0, a0, 0, 4);
        System.arraycopy(a0, 0, outData, 0, 4);
        System.arraycopy(a1, 0, outData, 4, 4);
    }

    public static void GOST_Magma_G_Fin(byte[] k, byte[] a, byte[] outData) {
        byte[] a0 = new byte[4];
        byte[] a1 = new byte[4];
        byte[] G = new byte[4];
        System.arraycopy(a, 4, a1, 0, 4);
        System.arraycopy(a, 0, a0, 0, 4);
        GOST_Magma_g(k, a0, G);
        GOST_Magma_Add(a1, G, G);
        System.arraycopy(G, 0, a1, 0, 4);
        System.arraycopy(a0, 0, outData, 0, 4);
        System.arraycopy(a1, 0, outData, 4, 4);
    }

    public static void GOST_Magma_Expand_Key(byte[] key) {
        System.arraycopy(key, 0, iterKey[7], 0, 4);
        System.arraycopy(key, 4, iterKey[6], 0, 4);
        System.arraycopy(key, 8, iterKey[5], 0, 4);
        System.arraycopy(key, 12, iterKey[4], 0, 4);
        System.arraycopy(key, 16, iterKey[3], 0, 4);
        System.arraycopy(key, 20, iterKey[2], 0, 4);
        System.arraycopy(key, 24, iterKey[1], 0, 4);
        System.arraycopy(key, 28, iterKey[0], 0, 4);
        System.arraycopy(key, 0, iterKey[15], 0, 4);
        System.arraycopy(key, 4, iterKey[14], 0, 4);
        System.arraycopy(key, 8, iterKey[13], 0, 4);
        System.arraycopy(key, 12, iterKey[12], 0, 4);
        System.arraycopy(key, 16, iterKey[11], 0, 4);
        System.arraycopy(key, 20, iterKey[10], 0, 4);
        System.arraycopy(key, 24, iterKey[9], 0, 4);
        System.arraycopy(key, 28, iterKey[8], 0, 4);
        System.arraycopy(key, 0, iterKey[23], 0, 4);
        System.arraycopy(key, 4, iterKey[22], 0, 4);
        System.arraycopy(key, 8, iterKey[21], 0, 4);
        System.arraycopy(key, 12, iterKey[20], 0, 4);
        System.arraycopy(key, 16, iterKey[19], 0, 4);
        System.arraycopy(key, 20, iterKey[18], 0, 4);
        System.arraycopy(key, 24, iterKey[17], 0, 4);
        System.arraycopy(key, 28, iterKey[16], 0, 4);
        System.arraycopy(key, 28, iterKey[31], 0, 4);
        System.arraycopy(key, 24, iterKey[30], 0, 4);
        System.arraycopy(key, 20, iterKey[29], 0, 4);
        System.arraycopy(key, 16, iterKey[28], 0, 4);
        System.arraycopy(key, 12, iterKey[27], 0, 4);
        System.arraycopy(key, 8, iterKey[26], 0, 4);
        System.arraycopy(key, 4, iterKey[25], 0, 4);
        System.arraycopy(key, 0, iterKey[24], 0, 4);
    }

    public static void GOST_Magma_Encrypt(byte[] blk, byte[] outBlk) {
        GOST_Magma_G(iterKey[0], blk, outBlk);
        for (int i = 1; i < 31; i++)
            GOST_Magma_G(iterKey[i], outBlk, outBlk);
        GOST_Magma_G_Fin(iterKey[31], outBlk, outBlk);
    }

    public static void GOST_Magma_Decrypt(byte[] blk, byte[] outBlk) {
        GOST_Magma_G(iterKey[31], blk, outBlk);
        for (int i = 30; i > 0; i--)
            GOST_Magma_G(iterKey[i], outBlk, outBlk);
        GOST_Magma_G_Fin(iterKey[0], outBlk, outBlk);
    }

    public static String encodeToString(byte[] array, int length) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < length; i++) {
            encoded.append((char) array[i]);
        }
        return encoded.toString();
    }

    public static void decodeFromString(String encoded, byte[] array, int length) {
        for (int i = 0; i < length; i++) {
            array[i] = (byte) encoded.charAt(i);
        }
    }
}
