package com.example.jjkdle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SixLetterWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String word;
    @Column(nullable = false)
    private Character l1;
    @Column(nullable = false)
    private Character l2;
    @Column(nullable = false)
    private Character l3;
    @Column(nullable = false)
    private Character l4;
    @Column(nullable = false)
    private Character l5;
    @Column(nullable = false)
    private Character l6;

    public SixLetterWord(Character l1, Character l2, Character l3, Character l4, Character l5, Character l6){
        this.l1 = Character.toUpperCase(l1);
        this.l2 = Character.toUpperCase(l2);
        this.l3 = Character.toUpperCase(l3);
        this.l4 = Character.toUpperCase(l4);
        this.l5 = Character.toUpperCase(l5);
        this.l6 = Character.toUpperCase(l6);
        StringBuilder sb = new StringBuilder();
        sb.append(this.l1).append(this.l2).append(this.l3).append(this.l4).append(this.l5).append(this.l6);
        this.word = sb.toString();
    }

    public List<Character> getLetters(){
        List<Character> letters = new ArrayList<>();
        letters.add(this.l1);
        letters.add(this.l2);
        letters.add(this.l3);
        letters.add(this.l4);
        letters.add(this.l5);
        letters.add(this.l6);

        return letters;
    }
}

