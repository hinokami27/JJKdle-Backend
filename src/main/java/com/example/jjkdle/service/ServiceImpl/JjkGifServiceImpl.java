package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.ColorResponse;
import com.example.jjkdle.model.DTO.JjkGifDTO;
import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.repository.JjkGifRepository;
import com.example.jjkdle.service.JjkCharacterService;
import com.example.jjkdle.service.JjkGifDateService;
import com.example.jjkdle.service.JjkGifService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JjkGifServiceImpl implements JjkGifService {
    private final JjkGifRepository gifRepository;
    private final JjkGifDateService gifDateService;
    private final JjkCharacterService jjkCharacterService;

    public JjkGifServiceImpl(JjkGifRepository gifRepository, JjkGifDateService gifDateService, JjkCharacterService jjkCharacterService) {
        this.gifRepository = gifRepository;
        this.gifDateService = gifDateService;
        this.jjkCharacterService = jjkCharacterService;
    }

    @Override
    public List<JjkGif> findAll() {
        return gifRepository.findAll();
    }

    @Override
    public List<JjkGifDTO> findAllDto() {
        return gifRepository.findAll()
                .stream()
                .map(character -> new JjkGifDTO(character.getGifUrl(), character.getCharacterName(), character.getAbility()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<JjkGif> findById(Long id) {
        return gifRepository.findById(id);
    }

    @Override
    public void save(String gifUrl, String characterName, String ability) {
        JjkGif gif = new JjkGif(gifUrl, characterName, ability);

        gifRepository.save(gif);
    }

    @Override
    public void edit(Long id, String gifUrl, String characterName, String ability) {
        JjkGif gif = gifRepository.findById(id).orElseThrow(RuntimeException::new);

        gif.setGifUrl(gifUrl);
        gif.setCharacterName(characterName);
        gif.setAbility(ability);

        gifRepository.save(gif);
    }

    @Override
    public void setLinks() {
        for (JjkGif gif : gifRepository.findAll()){
            String fab = gif.getAbility().replaceAll(":", "{");
            gif.setGifUrl("/images/photos/"+gif.getCharacterName()+"_"+fab+".gif");
            gifRepository.save(gif);
        }
    }

    @Override
    public ColorResponse compareGifs(String guessedCharacter, String guessedAbility) {
        JjkGif todaysGif = gifDateService.getTodayGif();

        ColorResponse response = new ColorResponse();

        int square = 0;


        if(guessedCharacter.equals(todaysGif.getCharacterName())){
            square++;
        }
        if(guessedAbility.equals(todaysGif.getAbility())){
            square++;
        }

        if(square==0){
            response.setColor("redSquare");
            response.setGuessed(false);
        }
        else if(square==1){
            response.setColor("yellowSquare");
            response.setGuessed(false);
        }
        else{
            response.setColor("greenSquare");
            response.setGuessed(true);
        }

        return response;
    }

    @Override
    public String getTodayCharImg() {
        String todayGif = gifDateService.getTodayGif().getCharacterName();
        return jjkCharacterService.findByName(todayGif).getImgUrl();
    }

    @Override
    public List<String> findAllAbilities() {
        List<JjkGif> gifs = gifRepository.findAll();
        List<String> abilities = new ArrayList<>();
        for(JjkGif gif : gifs){
            abilities.add(gif.getAbility());
        }
        return abilities;
    }

    @Override
    public void deleteAll() {
        gifRepository.deleteAll();
    }

    @Override
    public JjkGif findByNameAndAbility(String name, String ability) {
        return gifRepository.findByCharacterNameAndAbility(name, ability);
    }
}
