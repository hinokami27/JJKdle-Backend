package com.example.jjkdle.repository;

import com.example.jjkdle.model.SiegeCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiegeCharacterRepository extends JpaRepository<SiegeCharacter,Long> {
    SiegeCharacter findByName (String name);
}
