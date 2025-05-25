package com.example.jjkdle.service;

import com.example.jjkdle.model.ColorResponse;
import com.example.jjkdle.model.DTO.JjkGifDTO;
import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.model.JjkGifDate;

import java.util.List;
import java.util.Optional;

public interface JjkGifService {
    List<JjkGif> findAll();
    List<JjkGifDTO> findAllDto();
    Optional<JjkGif> findById(Long id);
    void save(String gifUrl, String characterName, String ability);
    void edit(Long id ,String gifUrl, String characterName, String ability);
    void setLinks();
    ColorResponse compareGifs(String guessedCharacter, String guessedAbility);
    String getTodayCharImg();
    List<String> findAllAbilities();
    void deleteAll();
    JjkGif findByNameAndAbility(String name, String ability);
}
