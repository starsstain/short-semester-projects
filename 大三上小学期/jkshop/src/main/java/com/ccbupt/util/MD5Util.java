package com.ccbupt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类，用于密码加密
 */
public class MD5Util {
    
    /**
     * 将字符串转换为MD5哈希值
     * @param input 输入字符串
     * @return MD5哈希值（32位小写）
     */
    public static String toMD5(String input) {
        if (input == null) {
            return null;
        }
        
        try {
            // 创建MD5消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // 计算哈希值
            byte[] messageDigest = md.digest(input.getBytes());
            
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
    
    /**
     * 验证密码是否匹配
     * @param inputPassword 输入的明文密码
     * @param storedHash 存储的MD5哈希值
     * @return 如果匹配返回true，否则返回false
     */
    public static boolean verifyPassword(String inputPassword, String storedHash) {
        if (inputPassword == null || storedHash == null) {
            return false;
        }
        
        String inputHash = toMD5(inputPassword);
        return inputHash.equals(storedHash);
    }
}