package com.example.jjkdle.web;

import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.model.SixLetterWord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.example.jjkdle.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/db")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3000/","http://localhost:5173/","http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/","https://www.jjkdle.com/","https://www.jjkdle.com/","https://r6dle-one.vercel.app/","https://r6dle-one.vercel.app"})
public class DbUpdateController {

    private final JjkCharacterService jjkCharacterService;
    private final JjkCharacterDateService jjkCharacterDateService;
    private final JjkGifService jjkGifService;
    private final JjkGifDateService jjkGifDateService;
    private final SiegeCharacterService siegeCharacterService;
    private final SiegeCharacterDateService siegeCharacterDateService;
    private final SLWService slwService;
    private final SLWDateService slwDateService;

    public DbUpdateController(JjkCharacterService jjkCharacterService, JjkCharacterDateService jjkCharacterDateService, JjkGifService jjkGifService, JjkGifDateService jjkGifDateService, SiegeCharacterService siegeCharacterService, SiegeCharacterDateService siegeCharacterDateService, SLWService slwService, SLWDateService slwDateService) {
        this.jjkCharacterService = jjkCharacterService;
        this.jjkCharacterDateService = jjkCharacterDateService;
        this.jjkGifService = jjkGifService;
        this.jjkGifDateService = jjkGifDateService;
        this.siegeCharacterService = siegeCharacterService;
        this.siegeCharacterDateService = siegeCharacterDateService;
        this.slwService = slwService;
        this.slwDateService = slwDateService;
    }
    @GetMapping("/purge")
    public void purgeDb() {
        jjkCharacterDateService.deleteAll();
        jjkCharacterService.deleteAll();
        jjkGifDateService.deleteAll();
        jjkGifService.deleteAll();
        siegeCharacterDateService.deleteAll();
        siegeCharacterService.deleteAll();
    }
    @GetMapping("/fetch")
    public void writeDbJson() throws IOException {
        List<JjkCharacter> jjkCharacters = jjkCharacterService.findAll();
        List<JjkGif> jjkgifs = jjkGifService.findAll();
        List<SiegeCharacter> operators = siegeCharacterService.findAll();
        List<SixLetterWord> words = slwService.findAll();

        ObjectMapper mapper = new ObjectMapper();

        File jjkCharFile = new File("jjkcharacters.json");
        File jjkGifFile = new File("jjkgifs.json");
        File operatorsFile = new File("operators.json");
        File wordsFile = new File("words.json");

        if (jjkCharFile.exists()) jjkCharFile.delete();
        if (jjkGifFile.exists()) jjkGifFile.delete();
        if (operatorsFile.exists()) operatorsFile.delete();
        if (wordsFile.exists()) wordsFile.delete();

        mapper.writerWithDefaultPrettyPrinter().writeValue(jjkCharFile, jjkCharacters);
        mapper.writerWithDefaultPrettyPrinter().writeValue(jjkGifFile, jjkgifs);
        mapper.writerWithDefaultPrettyPrinter().writeValue(operatorsFile, operators);
        mapper.writerWithDefaultPrettyPrinter().writeValue(wordsFile, words);
    }
    @GetMapping("/update")
    public void updateDb() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jjk = new File("jjkcharacters.json");
        File gif = new File("jjkgifs.json");
        File ops = new File("operators.json");
        File wds = new File("words.json");

        List<JjkCharacter> characters = mapper.readValue(jjk, new TypeReference<List<JjkCharacter>>() {});
        List<JjkGif> gifs = mapper.readValue(gif, new TypeReference<List<JjkGif>>() {});
        List<SiegeCharacter> operators = mapper.readValue(ops, new TypeReference<List<SiegeCharacter>>() {});
        List<SixLetterWord> words = mapper.readValue(wds, new TypeReference<List<SixLetterWord>>() {});

        for(JjkCharacter jjkchar : characters){
            if (jjkCharacterService.findByName(jjkchar.getName()) == null) {
                String aff = String.join(",", jjkchar.getAffiliations());
                String jjt = String.join(",", jjkchar.getJujutsu());
                String dom = String.join(",", jjkchar.getDomain());
                String nrg = String.join(",", jjkchar.getEnergy());

                jjkCharacterService.saveCharacter(
                        jjkchar.getName(), jjkchar.getImgUrl(), jjkchar.getGender(),
                        aff, jjt, dom, nrg, jjkchar.getGrade(), jjkchar.getFirstArc()
                );
            }}
        for(JjkGif jjkGif : gifs){
            if(jjkGifService.findByNameAndAbility(jjkGif.getCharacterName(), jjkGif.getAbility()) == null) {
                jjkGifService.save(jjkGif.getGifUrl(), jjkGif.getCharacterName(), jjkGif.getAbility());
            }
        }
        for(SiegeCharacter op : operators){
            if (siegeCharacterService.findSiegeCharacterByName(op.getName()) == null) {
                String specs = String.join(",", op.getSpecialty());
                String sights = String.join(",", op.getSights());

                siegeCharacterService.saveCharacter(
                        op.getImgUrl(), op.getName(), op.getGender(), op.getSide(),
                        specs, op.getOrganisation(), op.getSquad(), sights, op.getReleaseYear()
                );
            }}
        for (SixLetterWord word : words){
            slwService.createWord(word.getWord());
        }
        jjkCharacterDateService.shuffleAndSetDates();
        jjkGifDateService.shuffleAndSetDates();
        siegeCharacterDateService.shuffleAndSetDates();
        slwDateService.shuffleAndSetDates();
    }
}
