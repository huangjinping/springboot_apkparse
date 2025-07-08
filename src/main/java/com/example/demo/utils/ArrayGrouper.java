package com.example.demo.utils;

import java.util.Arrays;

public class ArrayGrouper {

    /**
     * 将字符串数组平均分成b组，并返回第i组
     *
     * @param a 原始字符串数组
     * @param b 要分成的组数
     * @param i 要返回的组索引(从0开始)
     * @return 第i组的字符串数组
     * @throws IllegalArgumentException 如果参数不合法
     */
    public static String[] getGroup(String[] a, int b, int i) {
        // 参数校验
        if (a == null || b <= 0 || i < 0 || i >= b) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        int totalSize = a.length;
        int baseSize = totalSize / b;  // 每组的基本大小
        int remainder = totalSize % b; // 余数，前remainder组每组多1个元素

        // 计算第i组的起始和结束索引
        int startIndex;
        int endIndex;

        if (i < remainder) {
            // 前remainder组，每组有baseSize + 1个元素
            startIndex = i * (baseSize + 1);
            endIndex = startIndex + baseSize + 1;
        } else {
            // 剩余组，每组有baseSize个元素
            startIndex = remainder * (baseSize + 1) + (i - remainder) * baseSize;
            endIndex = startIndex + baseSize;
        }

        // 确保结束索引不超过数组长度
        endIndex = Math.min(endIndex, totalSize);

        // 返回子数组
        return Arrays.copyOfRange(a, startIndex, endIndex);
    }

    // 测试方法
    public static void main(String[] args) {
        String[] testArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "i1", "i2"};
        int groupCount = 4;

        for (int i = 0; i < groupCount; i++) {
            String[] group = getGroup(testArray, groupCount, i);
            System.out.println("Group " + i + ": " + Arrays.toString(group));
        }
    }
}