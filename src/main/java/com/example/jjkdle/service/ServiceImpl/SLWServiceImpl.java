package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.SixLetterWord;
import com.example.jjkdle.repository.SLWRepository;
import com.example.jjkdle.service.SLWDateService;
import com.example.jjkdle.service.SLWService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SLWServiceImpl implements SLWService {

    private final SLWRepository slwRepository;
    private final SLWDateService slwDateService;

    public SLWServiceImpl(SLWRepository slwRepository, SLWDateService slwDateService){
        this.slwRepository = slwRepository;
        this.slwDateService = slwDateService;
    }

    private boolean isLetterPresent(SixLetterWord slw, Character charToMatch){
        String word = slw.getWord();
        for (int i=0; i<word.length(); i++){
            if(word.charAt(i) == charToMatch){
                return true;
            }
        }
        return false;
    }

    @Override
    public CompareResponse returnMatchingCharacters(String word) {
        SixLetterWord todaySlw = slwDateService.getTodaySlw();
        List<String> matchingLetters = new ArrayList<>();
        CompareResponse cr = new CompareResponse();
        int i = 0;
        for (Character letter: todaySlw.getLetters()){
            if(letter.equals(word.charAt(i))){
                matchingLetters.add("greenBox");
            }
            else if (isLetterPresent(todaySlw, word.charAt(i))){
                matchingLetters.add("yellowBox");
            }
            else{
                matchingLetters.add("redBox");
            }
            i++;
        }
        cr.setGuessed(true);
        for(String box: matchingLetters){
            if(!box.equals("greenBox")){
                cr.setGuessed(false);
            }
        }

        cr.setFirstSquare(matchingLetters.get(0));
        cr.setSecondSquare(matchingLetters.get(1));
        cr.setThirdSquare(matchingLetters.get(2));
        cr.setFourthSquare(matchingLetters.get(3));
        cr.setFifthSquare(matchingLetters.get(4));
        cr.setSixthSquare(matchingLetters.get(5));
        cr.setSeventhSquare("");
        return cr;
    }

    @Override
    public List<SixLetterWord> findAll() {
        return slwRepository.findAll();
    }

    @Override
    public Optional<SixLetterWord> findById(Long id) {
        return slwRepository.findById(id);
    }

    @Override
    public SixLetterWord createWord(String word) {
        SixLetterWord slw = new SixLetterWord(word.charAt(0), word.charAt(1), word.charAt(2), word.charAt(3), word.charAt(4), word.charAt(5));
        return slwRepository.save(slw);
    }

    @Override
    public SixLetterWord editWord(Long id, String word) {
        SixLetterWord slw = slwRepository.findById(id).orElseThrow();
        slw.setL1(Character.toUpperCase(word.charAt(0)));
        slw.setL2(Character.toUpperCase(word.charAt(1)));
        slw.setL3(Character.toUpperCase(word.charAt(2)));
        slw.setL4(Character.toUpperCase(word.charAt(3)));
        slw.setL5(Character.toUpperCase(word.charAt(4)));
        slw.setL6(Character.toUpperCase(word.charAt(5)));
        slwRepository.save(slw);
        return slw;
    }

    @Override
    public void deleteWord(Long id) {
        SixLetterWord slw = slwRepository.findById(id).orElseThrow();
        slwRepository.delete(slw);
    }
}