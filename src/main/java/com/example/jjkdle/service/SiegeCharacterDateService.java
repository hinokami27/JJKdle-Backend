package com.example.jjkdle.service;

import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.model.SiegeCharacterDate;

import java.util.List;

public interface SiegeCharacterDateService {

    void shuffleAndSetDates();
    SiegeCharacter getTodayCharacter();
    List<SiegeCharacterDate> findAll();
}
