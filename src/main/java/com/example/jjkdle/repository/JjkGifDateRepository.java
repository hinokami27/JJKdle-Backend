package com.example.jjkdle.repository;

import com.example.jjkdle.model.JjkGifDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface JjkGifDateRepository extends JpaRepository<JjkGifDate, Long> {
    JjkGifDate findJjkGifDateByDate(LocalDate date);
}
