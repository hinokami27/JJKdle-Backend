package com.example.jjkdle.repository;

import com.example.jjkdle.model.SixLetterWordDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SLWDateRepository extends JpaRepository<SixLetterWordDate, Long> {
    SixLetterWordDate findSixLetterWordDateByDate(LocalDate date);
}
