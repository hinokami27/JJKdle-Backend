package com.example.jjkdle.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompareResponse {

    private String genderSquare;
    private String affiliationsSquare;
    private String jujutsuSquare;
    private String domainSquare;
    private String energySquare;
    private String gradeSquare;
    private String arcSquare;
    private boolean guessed;

    public CompareResponse(String genderSquare, String affiliationsSquare, String jujutsuSquare, String domainSquare, String energySquare, String gradeSquare, String arcSquare, boolean guessed) {
        this.genderSquare = genderSquare;
        this.affiliationsSquare = affiliationsSquare;
        this.jujutsuSquare = jujutsuSquare;
        this.domainSquare = domainSquare;
        this.energySquare = energySquare;
        this.gradeSquare = gradeSquare;
        this.arcSquare = arcSquare;
        this.guessed = guessed;
    }

    public CompareResponse(String green) {
        this.genderSquare = green;
        this.affiliationsSquare = green;
        this.jujutsuSquare = green;
        this.domainSquare = green;
        this.energySquare = green;
        this.gradeSquare = green;
        this.arcSquare = green;
        this.guessed = true;
    }

    public CompareResponse() {
    }
}
