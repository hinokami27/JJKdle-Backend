package com.example.jjkdle.web;

import com.example.jjkdle.model.ApiCounter;
import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.DTO.JjkCharacterDTO;
import com.example.jjkdle.model.DTO.TodayCharacterDto;
import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.model.JjkCharacterDate;
import com.example.jjkdle.service.ApiCounterService;
import com.example.jjkdle.service.JjkCharacterDateService;
import com.example.jjkdle.service.JjkCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3000/","http://localhost:5173/","http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/","https://www.jjkdle.com/","https://www.jjkdle.com/"})
public class JjkCharacterRestController {

    private final JjkCharacterService jjkCharacterService;
    private final JjkCharacterDateService jjkCharacterDateService;
    private final ApiCounterService apiCounterService;

    public JjkCharacterRestController(JjkCharacterService jjkCharacterService, JjkCharacterDateService jjkCharacterDateService, ApiCounterService apiCounterService) {
        this.jjkCharacterService = jjkCharacterService;
        this.jjkCharacterDateService = jjkCharacterDateService;
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
    }

}
