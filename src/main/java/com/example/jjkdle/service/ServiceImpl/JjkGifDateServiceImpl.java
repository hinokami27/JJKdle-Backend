package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.model.JjkGifDate;
import com.example.jjkdle.repository.JjkGifDateRepository;
import com.example.jjkdle.repository.JjkGifRepository;
import com.example.jjkdle.service.JjkGifDateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class JjkGifDateServiceImpl implements JjkGifDateService {

    private final JjkGifDateRepository gifDateRepository;
    private final JjkGifRepository gifRepository;

    public JjkGifDateServiceImpl(JjkGifDateRepository gifDateRepository, JjkGifRepository gifRepository) {
        this.gifDateRepository = gifDateRepository;
        this.gifRepository = gifRepository;
    }

    @Override
    public void shuffleAndSetDates() {
        gifDateRepository.deleteAll();
        List<JjkGif> gifs = gifRepository.findAll();
        Collections.shuffle(gifs);
        LocalDate date = LocalDate.now();
        List<JjkGifDate> gifDates = new ArrayList<>();
        for (JjkGif gif : gifs){
            gifDates.add(new JjkGifDate(gif,date));
            date = date.plusDays(1);
        }
        gifDateRepository.saveAll(gifDates);
    }

    @Override
    public JjkGif getTodayGif() {
        return gifDateRepository.findJjkGifDateByDate(LocalDate.now()).getGif();
    }

    @Override
    public List<JjkGifDate> findAll() {
        return gifDateRepository.findAll();
    }
}
