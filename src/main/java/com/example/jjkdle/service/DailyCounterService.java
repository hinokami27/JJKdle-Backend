package com.example.jjkdle.service;

import com.example.jjkdle.model.DailyCounter;

public interface DailyCounterService {
    DailyCounter getDailyCounter();
    void resetDailyGuesses();
}
