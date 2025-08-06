package com.parch.combine.security.base;

import java.util.Base64;

public class SecurityUtil {

    private SecurityUtil() {}

    /**
     * 字节数组赚字符串（十六进制编码）
     *
     * @param hash 字节数字
     * @return 字符串
     */
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 字节数组转字符串（Base64编码）
     *
     * @param hash 字节数字
     * @return 字符串
     */
    public static String bytesToBase64(byte[] hash) {
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * 字符串转字节数组（Base64编码）
     *
     * @param base64 字节数字
     * @return 字符串
     */
    public static byte[] base64ToBytes(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
