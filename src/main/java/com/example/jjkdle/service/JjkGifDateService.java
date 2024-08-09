package com.example.jjkdle.service;

import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.model.JjkGif;

public interface JjkGifDateService {
    void shuffleAndSetDates();
    JjkGif getTodayGif();
}
