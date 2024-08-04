package com.example.jjkdle.web;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.service.JjkCharacterDateService;
import com.example.jjkdle.service.JjkCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173")
public class JjkCharacterRestController {

    private final JjkCharacterService jjkCharacterService;
    private final JjkCharacterDateService jjkCharacterDateService;

    public JjkCharacterRestController(JjkCharacterService jjkCharacterService, JjkCharacterDateService jjkCharacterDateService) {
        this.jjkCharacterService = jjkCharacterService;
        this.jjkCharacterDateService = jjkCharacterDateService;
    }
    @GetMapping("/all")
    public List<JjkCharacter> getAll(){
        return jjkCharacterService.findAll();
    }
    @GetMapping("/shuffle")
    public void shuffle() {
        jjkCharacterDateService.shuffleAndSetDates();
    }
    @GetMapping("/links")
    public void resetImgLinks() {
        jjkCharacterService.resetImgLinks();
    }
    @GetMapping("/doubles")
    public List<List<JjkCharacter>> doubles() {
        return jjkCharacterService.findCopies();
    }
    @GetMapping("/today")
    public JjkCharacter getTodaysCharacter() {
        return jjkCharacterDateService.getTodayCharacter();
    }
    @GetMapping("/compareWinner")
    public CompareResponse compareWinner(@RequestParam String name){
        return jjkCharacterService.compareWinner(name);
    }

}
