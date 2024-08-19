package com.example.jjkdle.web;

import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.model.JjkGifDate;
import com.example.jjkdle.service.JjkGifDateService;
import com.example.jjkdle.service.JjkGifService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gif")
@CrossOrigin(origins = {"http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/"})
public class JjkGifRestController {

    private final JjkGifService gifService;
    private final JjkGifDateService gifDateService;

    public JjkGifRestController(JjkGifService gifService, JjkGifDateService gifDateService) {
        this.gifService = gifService;
        this.gifDateService = gifDateService;
    }
    @GetMapping("/all")
    public List<JjkGif> getAllGifs(){
        return gifService.findAll();
    }
    @GetMapping("/dates")
    public List<JjkGifDate> getAllGifDates(){
        return gifDateService.findAll();
    }

    @GetMapping("/resetLinks")
    public void resetGifLinks() {
        gifService.setLinks();
    }

    @GetMapping("/today")
    public JjkGif getTodaysGif(){
        return gifDateService.getTodayGif();
    }

    @GetMapping("/shuffle")
    public void shuffle(){
        gifDateService.shuffleAndSetDates();
    }

    @GetMapping("/compareWinner")
    public String compareGifs(@RequestParam String guessedCharacter,
                              @RequestParam String guessedAbility){
        return gifService.compareGifs(guessedCharacter, guessedAbility);
    }
    @GetMapping("/abilities")
    public List<String> getAllAbilities(){
        return gifService.findAllAbilities();
    }
    @PostMapping("/addChars")
    public void addChars(@RequestBody List<JjkGif> gifs) {
        for(JjkGif gif : gifs){
            gifService.save(gif.getGifUrl(),gif.getCharacterName(),gif.getAbility());
        }
    }
}
