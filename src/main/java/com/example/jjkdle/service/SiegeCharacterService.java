package com.example.jjkdle.service;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.DTO.SiegeCharacterDto;
import com.example.jjkdle.model.SiegeCharacter;

import java.util.List;
import java.util.Optional;

public interface SiegeCharacterService {

    List<SiegeCharacter> findAll();

    void saveCharacter(String imgUrl, String name, String gender, String side, String specialty, String organisation, String squad, String sights, String releaseYear);

    Optional<SiegeCharacter> findById(Long id);

    void resetImgLinks();

    List<SiegeCharacterDto> findAllDto();

    boolean compared(SiegeCharacter first, SiegeCharacter second);

    CompareResponse compareWinner(String input);
    SiegeCharacter findSiegeCharacterByName(String name);

    void editCharacter(Long id, String imgUrl, String name, String gender, String side, String specialty, String organisation, String squad, String sights, String releaseYear);
    void deleteAll();
}
