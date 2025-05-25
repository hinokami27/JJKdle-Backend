package com.example.jjkdle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class SiegeCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgUrl;

    @Column(unique = true)
    private String name;

    private String gender;
    private String side;
    private List<String> specialty;
    private String organisation;
    private String squad;
    private List<String> sights;
    private String releaseYear;

    public SiegeCharacter(String imgUrl, String name, String gender, String side, List<String> specialty, String organisation, String squad, List<String> sights, String releaseYear) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.gender = gender;
        this.side = side;
        this.specialty = specialty;
        this.organisation = organisation;
        this.squad = squad;
        this.sights = sights;
        this.releaseYear = releaseYear;
    }

    public SiegeCharacter() {
    }
}
