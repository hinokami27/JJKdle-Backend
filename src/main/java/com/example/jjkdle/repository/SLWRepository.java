package com.example.jjkdle.repository;

import com.example.jjkdle.model.SixLetterWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SLWRepository extends JpaRepository<SixLetterWord, Long> {
}
