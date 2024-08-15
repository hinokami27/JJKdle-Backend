package com.example.jjkdle.service;

import com.example.jjkdle.model.SiegeCharacter;

public interface SiegeCharacterDateService {

    void shuffleAndSetDates();
    SiegeCharacter getTodayCharacter();
}
