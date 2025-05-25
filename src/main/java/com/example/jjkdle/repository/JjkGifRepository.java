package com.example.jjkdle.repository;

import com.example.jjkdle.model.JjkGif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JjkGifRepository extends JpaRepository<JjkGif, Long> {
    JjkGif findByCharacterNameAndAbility(String characterName, String ability);
}
