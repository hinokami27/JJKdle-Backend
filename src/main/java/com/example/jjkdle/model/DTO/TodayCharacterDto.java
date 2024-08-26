package com.example.jjkdle.model.DTO;

import com.example.jjkdle.config.EncryptionUtil;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodayCharacterDto {
    private String name;
    private String imgUrl;

    public TodayCharacterDto(String name, String imgUrl) {
        this.name = encryptData(name);
        this.imgUrl = encryptData(imgUrl);
    }

    private String encryptData(String data){
        return EncryptionUtil.encrypt(data);
    }


}
