package com.example.jjkdle.model.DTO;

import com.example.jjkdle.config.EncryptionUtil;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JjkGifDTO {
    private String gifUrl;
    private String characterName;
    private String ability;


    public JjkGifDTO(){}
    public JjkGifDTO(String gifUrl, String  characterName, String ability){
        this.gifUrl=encryptData(gifUrl);
        this.characterName=encryptData(characterName);
        this.ability=encryptData(ability);
    }

    private String encryptData(String data){
        try{
            return EncryptionUtil.encrypt(data);
        }catch (Exception e){
            return null;
        }
    }
}
