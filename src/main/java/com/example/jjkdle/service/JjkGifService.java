package com.example.jjkdle.service;

import com.example.jjkdle.model.JjkGif;

import java.util.List;
import java.util.Optional;

public interface JjkGifService {
    List<JjkGif> findAll();
    Optional<JjkGif> findById(Long id);
    void save(String gifUrl, String characterName, String ability);
    void edit(Long id ,String gifUrl, String characterName, String ability);
    void setLinks();
    String compareGifs(String guessedCharacter, String guessedAbility);
}
