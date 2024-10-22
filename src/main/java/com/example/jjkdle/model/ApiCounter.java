package com.example.jjkdle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ApiCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int counter;
    private LocalDate date;

    public ApiCounter() {
    }

    public ApiCounter(int counter, LocalDate date) {
        this.counter = counter;
        this.date = date;
    }
}
