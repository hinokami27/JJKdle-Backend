package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.repository.JjkGifDateRepository;
import com.example.jjkdle.repository.JjkGifRepository;
import com.example.jjkdle.service.JjkGifDateService;
import com.example.jjkdle.service.JjkGifService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JjkGifServiceImpl implements JjkGifService {
    private final JjkGifRepository gifRepository;
    private final JjkGifDateService gifDateService;

    public JjkGifServiceImpl(JjkGifRepository gifRepository, JjkGifDateService gifDateService) {
        this.gifRepository = gifRepository;
        this.gifDateService = gifDateService;
    }

    @Override
    public List<JjkGif> findAll() {
        return gifRepository.findAll();
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
        JjkGif gif = gifRepository.findById(id).orElseThrow(()-> new RuntimeException());

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
    public String compareGifs(String guessedCharacter, String guessedAbility) {
        JjkGif todaysGif = gifDateService.getTodayGif();

        int square = 0;

        String response;

        if(guessedCharacter.equals(todaysGif.getCharacterName())){
            square++;
        }
        if(guessedAbility.equals(todaysGif.getAbility())){
            square++;
        }

        if(square==0){
            response = "redSquare";
        }
        else if(square==1){
            response = "yellowSquare";
        }
        else{
            response = "greenSquare";
        }

        return response;
    }
}
