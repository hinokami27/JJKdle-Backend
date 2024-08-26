package com.example.jjkdle.model.DTO;

import com.example.jjkdle.config.EncryptionUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


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
            return EncryptionUtil.encrypt(data);
    }

    private List<String> encryptDataList(List<String> dataList) {
            return EncryptionUtil.encryptList(dataList);
    }
}
