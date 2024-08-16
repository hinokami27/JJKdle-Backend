package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.model.JjkCharacterDate;
import com.example.jjkdle.repository.JjkCharacterDateRepository;
import com.example.jjkdle.repository.JjkCharacterRepository;
import com.example.jjkdle.service.JjkCharacterDateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class JjkCharacterDateServiceImpl implements JjkCharacterDateService {

    private final JjkCharacterDateRepository jjkCharacterDateRepository;
    private final JjkCharacterRepository jjkCharacterRepository;

    public JjkCharacterDateServiceImpl(JjkCharacterDateRepository jjkCharacterDateRepository, JjkCharacterRepository jjkCharacterRepository) {
        this.jjkCharacterDateRepository = jjkCharacterDateRepository;
        this.jjkCharacterRepository = jjkCharacterRepository;
    }
    
    @Override
    public void shuffleAndSetDates() {
        jjkCharacterDateRepository.deleteAll();
        List<JjkCharacter> jjkCharacters = jjkCharacterRepository.findAll();
        Collections.shuffle(jjkCharacters);
        LocalDate date = LocalDate.now();
        List<JjkCharacterDate> jjkCharacterDates = new ArrayList<>();
        for (JjkCharacter jjkCharacter : jjkCharacters){
            jjkCharacterDates.add(new JjkCharacterDate(jjkCharacter,date));
            date = date.plusDays(1);
        }
        jjkCharacterDateRepository.saveAll(jjkCharacterDates);
    }

    @Override
    public JjkCharacter getTodayCharacter() {
        return jjkCharacterDateRepository.findJjkCharacterDateByDate(LocalDate.now()).getJjkCharacter();
    }

    @Override
    public List<JjkCharacterDate> findAll() {
        return jjkCharacterDateRepository.findAll();
    }
}
