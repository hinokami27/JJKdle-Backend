package com.example.jjkdle.web;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.service.DailyCounterService;
import com.example.jjkdle.service.JjkCharacterDateService;
import com.example.jjkdle.service.JjkCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/"})
public class JjkCharacterRestController {

    private final JjkCharacterService jjkCharacterService;
    private final JjkCharacterDateService jjkCharacterDateService;
    private final DailyCounterService dailyCounterService;

    public JjkCharacterRestController(JjkCharacterService jjkCharacterService, JjkCharacterDateService jjkCharacterDateService, DailyCounterService dailyCounterService) {
        this.jjkCharacterService = jjkCharacterService;
        this.jjkCharacterDateService = jjkCharacterDateService;
        this.dailyCounterService = dailyCounterService;
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
    @PostMapping("/addChars")
    public void addChars(@RequestBody List<JjkCharacter> jjkCharacters) {
        for(JjkCharacter jjk : jjkCharacters){
            String aff = String.join(",",jjk.getAffiliations());
            String jjt = String.join(",",jjk.getJujutsu());
            String dom = String.join(",",jjk.getDomain());
            String nrg = String.join(",",jjk.getEnergy());
            jjkCharacterService.saveCharacter(jjk.getName(),jjk.getImgUrl(),jjk.getGender(),aff,jjt,dom,nrg,jjk.getGrade(),jjk.getFirstArc());
        }
    }
    @GetMapping("/dailyWinners")
    public int dailyWinners(){
        return dailyCounterService.getDailyCounter().getDailyCounter().get();
    }
    @GetMapping("/resetDaily")
    public void resetDaily(){
        dailyCounterService.resetDailyGuesses();
    }


}
