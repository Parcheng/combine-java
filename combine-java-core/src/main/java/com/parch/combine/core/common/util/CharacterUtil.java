package com.parch.combine.core.common.util;

/**
 * 字符工具类
 */
public class CharacterUtil {

    public static String replaceChinese(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 判断字符是否为中文
            if (isChinese(ch) || isChinesePunctuation(ch)) {
                // 将字符转换为16进制
                String hex = Integer.toHexString(ch);
                // 拼接16进制表示，形如"\u4e2d"
                sb.append("\\u").append(hex);
            } else {
                // 非中文字符直接追加到结果中
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private static boolean isChinese(char ch) {
        // 判断字符是否为中文字符的Unicode编码范围
        return ch >= 0x4e00 && ch <= 0x9fa5;
    }

    private static boolean isChinesePunctuation(char ch) {
        // 判断字符是否为中文符号，可以根据需求自行添加或修改需要判断的中文符号
        return ch == '，' || ch == '。' || ch == '！' || ch == '？' || ch == '、'
                || ch == '；' || ch == '：' || ch == '“' || ch == '”'
                || ch == '【' || ch == '】' || ch == '（' || ch == '）' || ch == '　';
    }
}
