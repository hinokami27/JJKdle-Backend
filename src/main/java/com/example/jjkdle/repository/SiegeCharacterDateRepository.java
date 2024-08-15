package com.example.jjkdle.repository;

import com.example.jjkdle.model.SiegeCharacterDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SiegeCharacterDateRepository extends JpaRepository<SiegeCharacterDate,Long> {
    SiegeCharacterDate findSiegeCharacterDateByDate(LocalDate date);
}
