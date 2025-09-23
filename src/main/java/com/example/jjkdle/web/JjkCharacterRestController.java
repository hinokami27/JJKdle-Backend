package com.example.jjkdle.web;

import com.example.jjkdle.model.*;
import com.example.jjkdle.model.DTO.JjkCharacterDTO;
import com.example.jjkdle.model.DTO.TodayCharacterDto;
import com.example.jjkdle.service.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3000/","http://localhost:5173/","http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/","https://www.jjkdle.com/","https://www.jjkdle.com/","https://r6dle-one.vercel.app/","https://r6dle-one.vercel.app","https://jj-kdle-frontend-next.vercel.app"})
public class JjkCharacterRestController {

    private final JjkCharacterService jjkCharacterService;
    private final JjkCharacterDateService jjkCharacterDateService;
    private final JjkGifService jjkGifService;
    private final SiegeCharacterService siegeCharacterService;
    private final ApiCounterService apiCounterService;

    public JjkCharacterRestController(JjkCharacterService jjkCharacterService, JjkCharacterDateService jjkCharacterDateService, JjkGifService jjkGifService, SiegeCharacterService siegeCharacterService, ApiCounterService apiCounterService) {
        this.jjkCharacterService = jjkCharacterService;
        this.jjkCharacterDateService = jjkCharacterDateService;
        this.jjkGifService = jjkGifService;
        this.siegeCharacterService = siegeCharacterService;
        this.apiCounterService = apiCounterService;
    }
    @GetMapping("/all")
    public List<JjkCharacter> getAll(){
        return jjkCharacterService.findAll();
    }

    @GetMapping("/allDto")
    public List<JjkCharacterDTO> getAllDto(){
        apiCounterService.incrementApiCounter();
        return jjkCharacterService.findAllDto();
    }
    @GetMapping("/allCounters")
    public List<ApiCounter> getAllCounters(){
        return apiCounterService.getAllApiCounter();
    }

    @GetMapping("/dates")
    public List<JjkCharacterDate> getDates(){
        return jjkCharacterDateService.findAll();
    }
    @GetMapping("/shuffle")
    public void shuffle() {
        jjkCharacterDateService.shuffleAndSetDates();
    }
    @GetMapping("/links")
    public void resetImgLinks() {
        jjkCharacterService.resetImgLinks();
    }
    @GetMapping("/today")
    public JjkCharacter getTodaysCharacter() {
        return jjkCharacterDateService.getTodayCharacter();
    }

    @GetMapping("/todayDto")
    public TodayCharacterDto getTodaysCharacterDto(){
        return jjkCharacterDateService.getTodayCharacterDto();
    }

    @GetMapping("/compareWinner")
    public CompareResponse compareWinner(@RequestParam String name){
        return jjkCharacterService.compareWinner(name);
    }
    @GetMapping("/time")
    public String getZonedTime(){
        return jjkCharacterDateService.getCurrentTimeWithZone();
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
        jjkCharacterDateService.shuffleAndSetDates();
    }
    @PostMapping("/addAnalytics")
    public void addApiCounters(@RequestBody List<ApiCounter> apiCounters) {
        for(ApiCounter c : apiCounters){
            apiCounterService.saveApiCounter(c);
        }
    }
}
