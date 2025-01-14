package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TextUtils {

    public static String createName() {
//        String s = UUID.randomUUID().toString();
//        long l = System.currentTimeMillis();
//        return md5(s + l);
        return DateUtils.getCurrentString();

    }

    /**
     * change to md5
     *
     * @param string
     * @return
     */
    public static String md5(String string) {

        byte[] hash;

        try {

            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Huh, MD5 should be supported?", e);

        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {

            if ((b & 0xFF) < 0x10)
                hex.append("0");

            hex.append(Integer.toHexString(b & 0xFF));

        }

        return hex.toString().toUpperCase();
    }


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
