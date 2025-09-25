package com.example.jjkdle.service;

import com.example.jjkdle.model.SixLetterWord;
import com.example.jjkdle.model.SixLetterWordDate;

import java.util.List;

public interface SLWDateService {
    void shuffleAndSetDates();
    SixLetterWord getTodaySlw();
    List<SixLetterWordDate> findAll();
    void deleteAll();
}