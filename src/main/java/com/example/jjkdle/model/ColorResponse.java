package com.example.jjkdle.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorResponse {
    private String color;
    private boolean guessed;

    public ColorResponse(String color, boolean guessed) {
        this.color = color;
        this.guessed = guessed;
    }

    public ColorResponse() {
    }
}
