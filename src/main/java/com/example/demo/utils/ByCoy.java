package com.example.demo.utils;

import java.nio.charset.StandardCharsets;

public class ByCoy {
    public static byte[] b(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            if (i3 >= length2) {
                i3 = 0;
            }
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i3]);
            i2++;
            i3++;
        }
        return bArr;
    }

    public static String a(byte[] bArr, byte[] bArr2) {
        b(bArr, bArr2);
        return new String(bArr, StandardCharsets.UTF_8);
    }


    public static String edoe(byte[] bArr, byte[] bArr2) {
        return ByCoy.a(bArr, bArr2);
    }


    public static String l(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        int i = 0;
        int i3 = 0;
        while (i < length) {
            if (i3 >= length2) {
                i3 = 0;
            }
            bArr[i] = (byte) (bArr[i] ^ bArr2[i3]);
            i++;
            i3++;
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

}
