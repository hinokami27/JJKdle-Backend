package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.ApiCounter;
import com.example.jjkdle.repository.ApiCounterRepository;
import com.example.jjkdle.service.ApiCounterService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiCounterServiceImpl implements ApiCounterService {

    private final ApiCounterRepository apiCounterRepository;

    public ApiCounterServiceImpl(ApiCounterRepository apiCounterRepository) {
        this.apiCounterRepository = apiCounterRepository;
    }

    @Override
    public void incrementApiCounter() {
        LocalDate today = LocalDate.now();
        ApiCounter dailyCounter = apiCounterRepository.findByDate(today);
        if (dailyCounter == null) {
            ApiCounter newApiCounter = new ApiCounter(1,today);
            apiCounterRepository.save(newApiCounter);
        }
        else {
            dailyCounter.setCounter(dailyCounter.getCounter() + 1);
            apiCounterRepository.save(dailyCounter);
        }
    }

    @Override
    public int getTodayApiCounter() {
        return apiCounterRepository.findByDate(LocalDate.now()).getCounter();
    }

    @Override
    public List<ApiCounter> getAllApiCounter() {
        return apiCounterRepository.findAllByOrderByDateDesc();
    }

    @Override
    public int getTotalApiCounter() {
        List<ApiCounter> counters = apiCounterRepository.findAll();
        int total = 0;
        for (ApiCounter counter : counters) {
            total += counter.getCounter();
        }
        return total;
    }

    @Override
    public void saveApiCounter(ApiCounter apiCounter) {
        apiCounterRepository.save(apiCounter);
    }
}
