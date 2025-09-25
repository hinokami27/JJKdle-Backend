package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.SixLetterWord;
import com.example.jjkdle.model.SixLetterWordDate;
import com.example.jjkdle.repository.SLWDateRepository;
import com.example.jjkdle.repository.SLWRepository;
import com.example.jjkdle.service.SLWDateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SLWDateServiceImpl implements SLWDateService {
    private final SLWDateRepository slwDateRepository;
    private final SLWRepository slwRepository;

    public SLWDateServiceImpl(SLWDateRepository slwDateRepository, SLWRepository slwRepository) {
        this.slwDateRepository = slwDateRepository;
        this.slwRepository = slwRepository;
    }

    @Override
    public void shuffleAndSetDates() {
        slwDateRepository.deleteAll();
        List<SixLetterWord> slws = slwRepository.findAll();
        Collections.shuffle(slws);
        LocalDate date = LocalDate.now();
        List<SixLetterWordDate> slwDates = new ArrayList<>();
        for(SixLetterWord slw: slws){
            slwDates.add(new SixLetterWordDate(slw, date));
            date = date.plusDays(1);
        }
        slwDateRepository.saveAll(slwDates);
    }

    @Override
    public SixLetterWord getTodaySlw() {
        return slwDateRepository.findSixLetterWordDateByDate(LocalDate.now()).getSlw();
    }

    @Override
    public List<SixLetterWordDate> findAll() {
        return slwDateRepository.findAll();
    }

    @Override
    public void deleteAll() {
        slwDateRepository.deleteAll();
    }
}