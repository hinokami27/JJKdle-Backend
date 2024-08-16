package com.example.jjkdle.service.ServiceImpl;


import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.repository.SiegeCharacterRepository;
import com.example.jjkdle.service.SiegeCharacterDateService;
import com.example.jjkdle.service.SiegeCharacterService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SiegeCharacterServiceImpl implements SiegeCharacterService {

    private final SiegeCharacterRepository siegeCharacterRepository;
    private final SiegeCharacterDateService siegeCharacterDateService;

    public SiegeCharacterServiceImpl(SiegeCharacterRepository siegeCharacterRepository, SiegeCharacterDateService siegeCharacterDateService) {
        this.siegeCharacterRepository = siegeCharacterRepository;
        this.siegeCharacterDateService = siegeCharacterDateService;
    }

    @Override
    public List<SiegeCharacter> findAll() {
        return siegeCharacterRepository.findAll();
    }

    @Override
    public void saveCharacter(String imgUrl, String name, String gender, String side, String specialty, String organisation, String squad, String sights, String releaseYear) {
        List<String> spec = Arrays.stream(specialty.split(",")).map(String::trim).map(String::toUpperCase).sorted().collect(Collectors.toList());
        List<String> sight = Arrays.stream(sights.split(",")).map(String::trim).collect(Collectors.toList());
        SiegeCharacter character = new SiegeCharacter(imgUrl.trim(),name.trim().toUpperCase(),gender.trim().toUpperCase(),side.trim().toUpperCase(),spec,organisation.trim().toUpperCase(),squad.trim().toUpperCase(),sight,releaseYear.trim());
        siegeCharacterRepository.save(character);
    }

    @Override
    public Optional<SiegeCharacter> findById(Long id) {
        return siegeCharacterRepository.findById(id);
    }

    @Override
    public void resetImgLinks() {
        for (SiegeCharacter op : siegeCharacterRepository.findAll()){
            op.setImgUrl("/images/operators/"+op.getName()+".png");
            siegeCharacterRepository.save(op);
        }
    }

    @Override
    public boolean compared(SiegeCharacter first, SiegeCharacter second) {
        if(first.getName().equals(second.getName())){
            return true;
        }
        List<String> specs1 = first.getSpecialty();
        List<String> sight1 = first.getSights();
        List<String> specs2 = second.getSpecialty();
        List<String> sight2 = second.getSights();
        Collections.sort(specs1);
        Collections.sort(sight1);
        Collections.sort(specs2);
        Collections.sort(sight2);

        if(first.getGender().equals(second.getGender())
        && first.getSide().equals(second.getSide())
        && specs1.equals(specs2)
        && sight1.equals(sight2)
        && first.getOrganisation().equals(second.getOrganisation())
        && first.getSquad().equals(second.getSquad())
        && first.getReleaseYear().equals(second.getReleaseYear())){
            return true;
        }
        return false;
    }

    @Override
    public CompareResponse compareWinner(String input) {
        SiegeCharacter today = siegeCharacterDateService.getTodayCharacter();
        SiegeCharacter guess = siegeCharacterRepository.findByName(input);

        if(compared(today,guess)){
            return new CompareResponse("greenSquare");
        }
        CompareResponse response = new CompareResponse();
        response.setFirstSquare(stringCompare(today.getGender(),guess.getGender()));
        response.setSecondSquare(stringCompare(today.getSide(),guess.getSide()));
        response.setThirdSquare(listCompare(today.getSpecialty(),guess.getSpecialty()));
        response.setFourthSquare(stringCompare(today.getOrganisation(),guess.getOrganisation()));
        response.setFifthSquare(stringCompare(today.getSquad(),guess.getSquad()));
        response.setSixthSquare(listCompare(today.getSights(),guess.getSights()));
        response.setSeventhSquare(yearCompare(today.getReleaseYear(),guess.getReleaseYear()));
        return response;
    }

    @Override
    public void editCharacter(Long id, String imgUrl, String name, String gender, String side, String specialty, String organisation, String squad, String sights, String releaseYear) {
        SiegeCharacter operator = siegeCharacterRepository.findById(id).get();
        operator.setImgUrl(imgUrl);
        operator.setName(name);
        operator.setGender(gender);
        operator.setSide(side);
        List<String> spec = Arrays.stream(specialty.split(",")).map(String::trim).map(String::toUpperCase).sorted().collect(Collectors.toList());
        operator.setSpecialty(spec);
        operator.setOrganisation(organisation);
        operator.setSquad(squad);
        List<String> sight = Arrays.stream(sights.split(",")).map(String::trim).collect(Collectors.toList());
        operator.setSights(sight);
        operator.setReleaseYear(releaseYear);
        siegeCharacterRepository.save(operator);
    }
    public String listCompare(List<String> today, List<String> guess){
        Set<String> todaySet = new HashSet<>(today);
        Set<String> guessSet = new HashSet<>(guess);

        if (todaySet.equals(guessSet)) {
            return "greenSquare";
        } else if (Collections.disjoint(todaySet, guessSet)) {
            return "redSquare";
        } else {
            return "yellowSquare";
        }
    }
    public String stringCompare(String today, String guess){
        if(today.equals(guess)){
            return "greenSquare";
        }
        return "redSquare";
    }
    public String yearCompare(String today, String guess){
        int todayYear = Integer.parseInt(today);
        int guessYear = Integer.parseInt(guess);
        if(todayYear > guessYear){
            return "upArrow";
        }
        else if(todayYear < guessYear){
            return "downArrow";
        }
        else {
            return "greenSquare";
        }
    }
}
