package com.example.jjkdle.repository;

import com.example.jjkdle.model.JjkCharacterDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JjkCharacterDateRepository extends JpaRepository<JjkCharacterDate,Long> {
    JjkCharacterDate findJjkCharacterDateByDate(LocalDate date);
}
