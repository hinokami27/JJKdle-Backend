package com.example.jjkdle.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompareResponse {

    private String firstSquare;
    private String secondSquare;
    private String thirdSquare;
    private String fourthSquare;
    private String fifthSquare;
    private String sixthSquare;
    private String seventhSquare;
    private boolean guessed;

    public CompareResponse(String firstSquare, String secondSquare, String thirdSquare, String fourthSquare, String fifthSquare, String sixthSquare, String seventhSquare, boolean guessed) {
        this.firstSquare = firstSquare;
        this.secondSquare = secondSquare;
        this.thirdSquare = thirdSquare;
        this.fourthSquare = fourthSquare;
        this.fifthSquare = fifthSquare;
        this.sixthSquare = sixthSquare;
        this.seventhSquare = seventhSquare;
        this.guessed = guessed;
    }

    public CompareResponse(String green) {
        this.firstSquare = green;
        this.secondSquare = green;
        this.thirdSquare = green;
        this.fourthSquare = green;
        this.fifthSquare = green;
        this.sixthSquare = green;
        this.seventhSquare = green;
        this.guessed = true;
    }

    public CompareResponse() {
    }
}
