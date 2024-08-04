package com.example.jjkdle.repository;

import com.example.jjkdle.model.JjkCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JjkCharacterRepository extends JpaRepository<JjkCharacter,Long> {
    JjkCharacter findByName(String name);
}
