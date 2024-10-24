package com.example.jjkdle.model.DTO;

import com.example.jjkdle.config.EncryptionUtil;
import com.example.jjkdle.model.SiegeCharacter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SiegeCharacterDto {

    private String imgUrl;
    private String name;

    private String gender;
    private String side;
    private List<String> specialty;
    private String organisation;
    private String squad;
    private List<String> sights;
    private String releaseYear;

    public SiegeCharacterDto(SiegeCharacter operator) {
        this.imgUrl = encryptData(operator.getImgUrl());
        this.name = encryptData(operator.getName());
        this.gender = encryptData(operator.getGender());
        this.side = encryptData(operator.getSide());
        this.specialty = encryptDataList(operator.getSpecialty());
        this.organisation = encryptData(operator.getOrganisation());
        this.squad = encryptData(operator.getSquad());
        this.sights = encryptDataList(operator.getSights());
        this.releaseYear = encryptData(operator.getReleaseYear());
    }

    private String encryptData(String data){
        return EncryptionUtil.encrypt(data);
    }

    private List<String> encryptDataList(List<String> dataList) {
        return EncryptionUtil.encryptList(dataList);
    }
}
