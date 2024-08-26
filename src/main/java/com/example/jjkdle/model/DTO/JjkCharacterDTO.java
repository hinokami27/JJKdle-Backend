package com.example.jjkdle.model.DTO;

import com.example.jjkdle.config.EncryptionUtil;
import com.example.jjkdle.model.JjkGif;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class JjkCharacterDTO {
    private String name;
    private String imgUrl;
    private String gender;


    private List<String> affiliations;

    private List<String> jujutsu;

    private List<String> domain;

    private List<String> energy;

    private String grade;
    private String firstArc;

    private static final String SECRET_KEY = "IlijaMizimakoski";

    public JjkCharacterDTO(){}

    public JjkCharacterDTO(String name, String imgUrl, String gender, List<String> affiliations, List<String> jujutsu, List<String> domain, List<String> energy, String grade, String firstArc){
        this.name = encryptData(name);
        this.imgUrl = encryptData(imgUrl);
        this.gender = encryptData(gender);
        this.affiliations = encryptDataList(affiliations);
        this.jujutsu = encryptDataList(jujutsu);
        this.domain = encryptDataList(domain);
        this.energy = encryptDataList(energy);
        this.grade = encryptData(grade);
        this.firstArc = encryptData(firstArc);
    }

    private String encryptData(String data){
        try{
            return EncryptionUtil.encrypt(data, SECRET_KEY);
        }catch (Exception e){
            return null;
        }
    }

    private List<String> encryptDataList(List<String> dataList) {
        try {
            return EncryptionUtil.encryptList(dataList, SECRET_KEY);
        } catch (Exception e) {
            return null;
        }
    }

    public String decryptData(String encryptedData){
        try {
            return EncryptionUtil.decrypt(encryptedData, SECRET_KEY);
        }catch (Exception e){
            return null;
        }
    }

    public List<String> decryptDataList(List<String> encryptedList) {
        try {
            return EncryptionUtil.decryptList(encryptedList, SECRET_KEY);
        } catch (Exception e) {
            return null;
        }
    }
}
