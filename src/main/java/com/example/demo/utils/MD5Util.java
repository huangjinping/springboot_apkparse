package com.example.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;


/**
 * @author Administrator
 * @ Created by Administrator on 2014/11/26.
 */
public class MD5Util {
    private static final Logger log = LoggerFactory.getLogger(MD5Util.class);

    private static char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getMd5Sum(String inputStr) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] inputStrByte = inputStr.getBytes();
        digest.update(inputStrByte, 0, inputStrByte.length);


        byte[] md5sum = digest.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            char[] ob = new char[2];
            ob[0] = Digit[md5sum[i] >> 4 & 0x0F];
            ob[1] = Digit[md5sum[i] & 0x0F];
            String s = new String(ob);
            sb.append(s);
        }

        return sb.toString();
    }


    /**
     * 对字符串进行 MD5 加密
     *
     * @param str 待加密字符串
     * @return 加密后字符串
     */
    public static String md5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 error " + e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
        byte[] encodedValue = md5.digest();
        int j = encodedValue.length;
        char finalValue[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte encoded = encodedValue[i];
            finalValue[k++] = Digit[encoded >> 4 & 0xf];
            finalValue[k++] = Digit[encoded & 0xf];
        }

        return new String(finalValue);
    }

    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @return 签名结果
     */
    public static boolean verify(String text, String sign) {
        String mysign = md5(text);
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 对文件进行 MD5 加密
     *
     * @param file 待加密的文件
     * @return 文件加密后的 MD5 值
     * @throws IOException
     */
    public static String md5(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            int n = 0;
            byte[] buffer = new byte[1024];
            do {
                n = is.read(buffer);
                if (n > 0) {
                    md5.update(buffer, 0, n);
                }
            } while (n != -1);
            is.skip(0);
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 error " + e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } finally {
            is.close();
        }

        byte[] encodedValue = md5.digest();

        int j = encodedValue.length;
        char finalValue[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte encoded = encodedValue[i];
            finalValue[k++] = Digit[encoded >> 4 & 0xf];
            finalValue[k++] = Digit[encoded & 0xf];
        }

        return new String(finalValue);
    }

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String input_charset) {
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串(默认字符集utf-8)
     *
     * @param text 需要签名的字符串
     * @return 签名结果
     */
    public static String sign(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, "utf-8"));
    }

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param sign          签名结果
     * @param key           密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key,
                                 String input_charset) {
        text = text + key;
        String mysign = DigestUtils
                .md5Hex(getContentBytes(text, input_charset));


        if (mysign.equals(sign)) {
            return true;
        } else {
            log.info("请求参数" + text + "客户端传来签名:" + sign + ",服务器校验签名: " + mysign);

            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:"
                    + charset);
        }
    }


    /**
     * 专为 绑定参数接口加密
     */
    public static String signBind(String text) {
        return DigestUtils.md5Hex("&*^(%^sersqwwe" + getContentBytes(text, "utf-8") + "_bind_!@#!@#!");
    }


    public static void main(String[] args) {
//System.out.println(sign("111111"+PropertiesUtil.config.get("md5.key")));
//		System.out.println(sign("20150304"
//				+ PropertiesUtil.config.get("md5.key")));
//		System.out.println(sign("admin!!!!!"
//				+ PropertiesUtil.config.get("md5.key")));
//		System.out.println(sign("niuer!!!!"
//				+ PropertiesUtil.config.get("md5.key")));
        //	System.out.println(signBind(1+"d"+"d"));
        //System.out.println(sign("yxy_yaning!@#"+PropertiesUtil.config.get("md5.key")));
        System.out.println(sign("yxy_djt)(*"));

    }
}
