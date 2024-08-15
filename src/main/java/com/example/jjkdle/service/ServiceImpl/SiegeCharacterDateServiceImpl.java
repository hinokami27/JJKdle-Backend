package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.model.SiegeCharacterDate;
import com.example.jjkdle.repository.SiegeCharacterDateRepository;
import com.example.jjkdle.repository.SiegeCharacterRepository;
import com.example.jjkdle.service.SiegeCharacterDateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SiegeCharacterDateServiceImpl implements SiegeCharacterDateService {

    private final SiegeCharacterDateRepository siegeCharacterDateRepository;
    private final SiegeCharacterRepository siegeCharacterRepository;

    public SiegeCharacterDateServiceImpl(SiegeCharacterDateRepository siegeCharacterDateRepository, SiegeCharacterRepository siegeCharacterRepository) {
        this.siegeCharacterDateRepository = siegeCharacterDateRepository;
        this.siegeCharacterRepository = siegeCharacterRepository;
    }

    @Override
    public void shuffleAndSetDates() {
        siegeCharacterDateRepository.deleteAll();
        List<SiegeCharacter> operators = siegeCharacterRepository.findAll();
        Collections.shuffle(operators);
        LocalDate date = LocalDate.now();
        List<SiegeCharacterDate> siegeCharacterDates = new ArrayList<>();
        for(SiegeCharacter operator : operators){
            siegeCharacterDates.add(new SiegeCharacterDate(operator,date));
            date = date.plusDays(1);
        }
        siegeCharacterDateRepository.saveAll(siegeCharacterDates);
    }

    @Override
    public SiegeCharacter getTodayCharacter() {
        return siegeCharacterDateRepository.findSiegeCharacterDateByDate(LocalDate.now()).getSiegeCharacter();
    }
}
