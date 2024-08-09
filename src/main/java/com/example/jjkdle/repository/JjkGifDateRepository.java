package com.example.jjkdle.repository;

import com.example.jjkdle.model.JjkCharacterDate;
import com.example.jjkdle.model.JjkGifDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface JjkGifDateRepository extends JpaRepository<JjkGifDate, Long> {
    JjkGifDate findJjkGifDateByDate(LocalDate date);
}
