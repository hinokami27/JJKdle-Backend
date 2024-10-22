package com.example.jjkdle.service;

import com.example.jjkdle.model.ApiCounter;

import java.util.List;

public interface ApiCounterService {
    void incrementApiCounter();
    int getTodayApiCounter();
    List<ApiCounter> getAllApiCounter();
    int getTotalApiCounter();
}
