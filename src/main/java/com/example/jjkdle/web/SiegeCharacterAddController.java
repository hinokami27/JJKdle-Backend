package com.example.jjkdle.web;


import com.example.jjkdle.model.SiegeCharacter;
import com.example.jjkdle.service.SiegeCharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/siege")
public class SiegeCharacterAddController {

    private final SiegeCharacterService siegeCharacterService;

    public SiegeCharacterAddController(SiegeCharacterService siegeCharacterService) {
        this.siegeCharacterService = siegeCharacterService;
    }

    @GetMapping
    public String getAddPage(){
        return "addOperator";
    }

    @GetMapping("/showAll")
    public String showAll(Model model){
        List<SiegeCharacter> operators = siegeCharacterService.findAll();
        operators.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getReleaseYear())));
        model.addAttribute("operators", operators);
        return "siegeShowAll";
    }

    @PostMapping("/add")
    public String addChar(@RequestParam String imgUrl,
                          @RequestParam String name,
                          @RequestParam String gender,
                          @RequestParam String side,
                          @RequestParam String specialty,
                          @RequestParam String organisation,
                          @RequestParam String squad,
                          @RequestParam String sights,
                          @RequestParam String releaseYear) {
        siegeCharacterService.saveCharacter(imgUrl, name, gender, side, specialty, organisation, squad, sights, releaseYear);
        return "redirect:/siege";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        if(siegeCharacterService.findById(id).isPresent()){
            SiegeCharacter operator = siegeCharacterService.findById(id).get();

            String spec = String.join(",", operator.getSpecialty());
            String sight = String.join(",", operator.getSights());
            model.addAttribute("operator", operator);
            model.addAttribute("spec", spec);
            model.addAttribute("sight", sight);
        }
        return "siegeEdit";
    }
    @PostMapping("/edit")
    public String editChar(@RequestParam(required = false) Long id,
                           @RequestParam String imgUrl,
                           @RequestParam String name,
                           @RequestParam String gender,
                           @RequestParam String side,
                           @RequestParam String specialty,
                           @RequestParam String organisation,
                           @RequestParam String squad,
                           @RequestParam String sights,
                           @RequestParam String releaseYear) {
        siegeCharacterService.editCharacter(id,imgUrl, name, gender, side, specialty, organisation, squad, sights, releaseYear);
        return "redirect:/siege/showAll";
    }

}
