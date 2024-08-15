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
public class SiegeCharacterDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private SiegeCharacter siegeCharacter;
    private LocalDate date;

    public SiegeCharacterDate(SiegeCharacter siegeCharacter, LocalDate date) {
        this.siegeCharacter = siegeCharacter;
        this.date = date;
    }
}
