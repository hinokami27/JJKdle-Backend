package com.example.jjkdle.service;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.SixLetterWord;

import java.util.List;
import java.util.Optional;

public interface SLWService {
    CompareResponse returnMatchingCharacters(String word);
    List<SixLetterWord> findAll();
    Optional<SixLetterWord> findById(Long id);
    SixLetterWord createWord(String word);
    SixLetterWord editWord(Long id, String word);
    void deleteWord(Long id);
    void deleteAllWords();
}
