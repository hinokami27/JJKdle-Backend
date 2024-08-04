package com.example.jjkdle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JjkCharacterDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private JjkCharacter jjkCharacter;
    private LocalDate date;

    public JjkCharacterDate(JjkCharacter jjkCharacter, LocalDate date) {
        this.jjkCharacter = jjkCharacter;
        this.date = date;
    }
}
