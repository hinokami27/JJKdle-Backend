package com.example.jjkdle.service;

import com.example.jjkdle.model.JjkCharacter;

public interface JjkCharacterDateService {

    void shuffleAndSetDates();
    JjkCharacter getTodayCharacter();
}
