package com.example.jjkdle.model.DTO;

import com.example.jjkdle.config.EncryptionUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JjkGifDTO {
    private String gifUrl;
    private String characterName;
    private String ability;

    private static final String SECRET_KEY = "IlijaMizimakoski";

    public JjkGifDTO(){}
    public JjkGifDTO(String gifUrl, String  characterName, String ability){
        this.gifUrl=encryptData(gifUrl);
        this.characterName=encryptData(characterName);
        this.ability=encryptData(ability);
    }

    private String encryptData(String data){
        try{
            return EncryptionUtil.encrypt(data, SECRET_KEY);
        }catch (Exception e){
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

}
