package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.repository.SiegeCharacterRepository;
import com.example.jjkdle.service.SiegeCharacterService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SiegeCharacterServiceImpl implements SiegeCharacterService {

    private final SiegeCharacterRepository siegeCharacterRepository;

    public SiegeCharacterServiceImpl(SiegeCharacterRepository siegeCharacterRepository) {
        this.siegeCharacterRepository = siegeCharacterRepository;
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
}
