package com.example.jjkdle.web;

import com.example.jjkdle.service.SiegeCharacterService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/siege")
@CrossOrigin(origins = {"http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/"})
public class SiegeCharacterRestController {

    private final SiegeCharacterService siegeCharacterService;

    public SiegeCharacterRestController(SiegeCharacterService siegeCharacterService) {
        this.siegeCharacterService = siegeCharacterService;
    }

    @GetMapping("/links")
    public void resetImgLinks() {
        siegeCharacterService.resetImgLinks();
    }
}
