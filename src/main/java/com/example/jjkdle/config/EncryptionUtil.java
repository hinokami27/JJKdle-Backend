package com.example.jjkdle.config;

import java.util.List;
import java.util.stream.Collectors;

public class EncryptionUtil {
    public static String encrypt(String data){
        StringBuilder sb = new StringBuilder();
        for(char c : data.toCharArray()){
            c+=5;
            sb.append(c);
        }
        return sb.toString();
    }
    public static List<String> encryptList(List<String> dataList){
        return dataList.stream()
                .map(data -> {
                    try {
                        return encrypt(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
