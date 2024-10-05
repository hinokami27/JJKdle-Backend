package com.example.jjkdle.web;

import com.example.jjkdle.model.ColorResponse;
import com.example.jjkdle.model.DTO.JjkGifDTO;
import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.model.JjkGifDate;
import com.example.jjkdle.service.JjkGifDateService;
import com.example.jjkdle.service.JjkGifService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/gif")
@CrossOrigin(origins = {"http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/","https://www.jjkdle.com/","https://www.jjkdle.com/"})
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

    @GetMapping("/allDto")
    public List<JjkGifDTO> getAllGifsDto(){
        return gifService.findAllDto();
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

    @GetMapping("/todayDto")
    public JjkGifDTO getTodaysGifDto(){
        return gifDateService.getTodayGifDto();
    }

    @GetMapping("/shuffle")
    public void shuffle(){
        gifDateService.shuffleAndSetDates();
    }

    @GetMapping("/compareWinner")
    public ColorResponse compareGifs(@RequestParam String guessedCharacter,
                                     @RequestParam String guessedAbility){
        return gifService.compareGifs(guessedCharacter, guessedAbility);
    }
    @GetMapping("/abilities")
    public List<String> getAllAbilities(){
        Set<String> abilities = new HashSet<>(gifService.findAllAbilities());
        return new ArrayList<>(abilities);
    }
    @PostMapping("/addChars")
    public void addChars(@RequestBody List<JjkGif> gifs) {
        for(JjkGif gif : gifs){
            gifService.save(gif.getGifUrl(),gif.getCharacterName(),gif.getAbility());
        }
    }
    @GetMapping("/todayImg")
    public String getTodayCharImg(){
        return gifService.getTodayCharImg();
    }
}
