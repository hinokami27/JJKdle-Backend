package com.example.jjkdle.service;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.JjkCharacter;

import java.util.List;
import java.util.Optional;

public interface JjkCharacterService {

    void saveCharacter(String name, String imgUrl, String gender, String affiliations, String jujutsu, String domain, String energy, String grade, String firstArc);
    void editCharacter(Long id, String name, String imgUrl, String gender, String affiliations, String jujutsu, String domain, String energy, String grade, String firstArc);
    List<JjkCharacter> findAll();
    Optional<JjkCharacter> findById(Long id);

    List<List<JjkCharacter>> findCopies();

    boolean compared(JjkCharacter first, JjkCharacter second);

    void resetImgLinks();

    CompareResponse compareWinner(String input);
}
