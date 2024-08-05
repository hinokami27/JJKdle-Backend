package com.example.jjkdle.service;

import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.model.JjkCharacterDate;

import java.util.List;

public interface JjkCharacterDateService {

    public void shuffleAndSetDates();
    public JjkCharacter getTodayCharacter();
}
