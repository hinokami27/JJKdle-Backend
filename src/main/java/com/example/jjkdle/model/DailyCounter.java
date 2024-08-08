package com.example.jjkdle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Setter
public class DailyCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private AtomicInteger dailyCounter;

    public DailyCounter() {
    }

    public DailyCounter(AtomicInteger dailyCounter) {
        this.dailyCounter = dailyCounter;
    }
}
