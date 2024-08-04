package com.example.jjkdle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@SuppressWarnings("ALL")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class JjkCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String imgUrl;
    private String gender;


    private List<String> affiliations;

    private List<String> jujutsu;

    private List<String> domain;

    private List<String> energy;

    private String grade;
    private String firstArc;

    public JjkCharacter(String name, String imgUrl, String gender, List<String> affiliations, List<String> jujutsu, List<String> domain, List<String> energy, String grade, String firstArc) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.gender = gender;
        this.affiliations = affiliations;
        this.jujutsu = jujutsu;
        this.domain = domain;
        this.energy = energy;
        this.grade = grade;
        this.firstArc = firstArc;
    }
}
