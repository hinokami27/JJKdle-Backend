package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.DailyCounter;
import com.example.jjkdle.repository.DailyCounterRepository;
import com.example.jjkdle.service.DailyCounterService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DailyCounterServiceImpl implements DailyCounterService {

    private final DailyCounterRepository dailyCounterRepository;

    public DailyCounterServiceImpl(DailyCounterRepository dailyCounterRepository) {
        this.dailyCounterRepository = dailyCounterRepository;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyGuesses() {
        dailyCounterRepository.deleteAll();
        AtomicInteger ai = new AtomicInteger(0);
        DailyCounter dc = new DailyCounter(ai);
        dailyCounterRepository.save(dc);
    }

    @Override
    public DailyCounter getDailyCounter() {
        return dailyCounterRepository.findAll().stream().findFirst().get();

    }
}
