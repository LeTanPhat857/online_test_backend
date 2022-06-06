package com.sunflower.onlinetest.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomBase64Test {

    @Test
    void encode() {
        String plainText = "9";
        String cipherText = "OQ==";

        CustomBase64 customBase64 = new CustomBase64();

        Assertions.assertEquals(cipherText, customBase64.encode(plainText));
    }

    @Test
    void decode() {
        String plainText = "9";
        String cipherText = "OQ==";

        CustomBase64 customBase64 = new CustomBase64();

        Assertions.assertEquals(plainText, customBase64.decode(cipherText));
    }
}