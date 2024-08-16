package com.example.jjkdle.web;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.model.SiegeCharacterDate;
import com.example.jjkdle.service.SiegeCharacterDateService;
import com.example.jjkdle.service.SiegeCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/siege")
@CrossOrigin(origins = {"http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/"})
public class SiegeCharacterRestController {

    private final SiegeCharacterService siegeCharacterService;
    private final SiegeCharacterDateService siegeCharacterDateService;

    public SiegeCharacterRestController(SiegeCharacterService siegeCharacterService, SiegeCharacterDateService siegeCharacterDateService) {
        this.siegeCharacterService = siegeCharacterService;
        this.siegeCharacterDateService = siegeCharacterDateService;
    }
    @GetMapping("/all")
    public List<SiegeCharacter> getAll(){
        return siegeCharacterService.findAll();
    }
    @GetMapping("/dates")
    public List<SiegeCharacterDate> getDates(){
        return siegeCharacterDateService.findAll();
    }
    @GetMapping("/shuffle")
    public void shuffle() {
        siegeCharacterDateService.shuffleAndSetDates();
    }
    @GetMapping("/links")
    public void resetImgLinks() {
        siegeCharacterService.resetImgLinks();
    }
    @GetMapping("/today")
    public SiegeCharacter getTodaysCharacter() {
        return siegeCharacterDateService.getTodayCharacter();
    }
    @GetMapping("/compareWinner")
    public CompareResponse compareWinner(@RequestParam String name){
        return siegeCharacterService.compareWinner(name);
    }

}
