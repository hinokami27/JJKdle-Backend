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
public class JjkGifDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private JjkGif gif;
    private LocalDate date;

    public JjkGifDate(JjkGif gif, LocalDate date){
        this.gif = gif;
        this.date = date;
    }
}
