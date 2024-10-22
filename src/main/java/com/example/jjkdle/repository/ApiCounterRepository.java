package com.example.jjkdle.repository;

import com.example.jjkdle.model.ApiCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ApiCounterRepository extends JpaRepository<ApiCounter, Long> {

    ApiCounter findByDate(LocalDate date);
}
