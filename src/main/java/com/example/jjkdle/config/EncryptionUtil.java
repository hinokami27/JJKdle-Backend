package com.example.jjkdle.config;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class EncryptionUtil {
    private static final String ALGORITHM = "AES";

    private static SecretKeySpec getSecretKey(String secret) throws Exception{
        byte[] key = secret.getBytes("UTF-8");
        return new SecretKeySpec(key, ALGORITHM);
    }

    public static String encrypt(String data, String secret) throws Exception{
        SecretKeySpec secretKey = getSecretKey(secret);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData, String secret) throws Exception{
        SecretKeySpec secretKey = getSecretKey(secret);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] original = cipher.doFinal(decodedBytes);
        return new String(original, "UTF-8");
    }

    public static List<String> encryptList(List<String> dataList, String secret) throws Exception {
        return dataList.stream()
                .map(data -> {
                    try {
                        return encrypt(data, secret);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    public static List<String> decryptList(List<String> encryptedList, String secret) throws Exception {
        return encryptedList.stream()
                .map(data -> {
                    try {
                        return decrypt(data, secret);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
