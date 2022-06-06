package com.sunflower.onlinetest.util;

import javax.ejb.Singleton;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Singleton
public class CustomBase64 {

    public static String encode(String plainText) {
        return new String(Base64.getEncoder().encode(plainText.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public static String decode(String cipherText) {
        return new String(Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public static Integer decodeAsInteger(String cipherText) {
        return Integer.parseInt(decode(cipherText));
    }
}
