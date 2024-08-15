package com.example.jjkdle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class JjkGif {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String gifUrl;
    private String characterName;
    private String ability;

    public JjkGif(String gifUrl, String  characterName, String ability){
        this.gifUrl=gifUrl;
        this.characterName=characterName;
        this.ability=ability;
    }
}
