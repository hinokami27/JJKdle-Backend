package com.example.jjkdle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SixLetterWordDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private SixLetterWord slw;
    private LocalDate date;

    public SixLetterWordDate(SixLetterWord slw, LocalDate date){
        this.slw = slw;
        this.date = date;
    }
}
