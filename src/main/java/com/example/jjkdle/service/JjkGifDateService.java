package com.example.jjkdle.service;

import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.model.JjkGifDate;

import java.util.List;

public interface JjkGifDateService {
    void shuffleAndSetDates();
    JjkGif getTodayGif();
    List<JjkGifDate> findAll();
}
