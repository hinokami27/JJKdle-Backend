package com.example.jjkdle.web;

import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.service.ApiCounterService;
import com.example.jjkdle.service.JjkCharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class JjkCharacterAddController {

    private final JjkCharacterService jjkCharacterService;
    private final ApiCounterService apiCounterService;

    public JjkCharacterAddController(JjkCharacterService jjkCharacterService, ApiCounterService apiCounterService) {
        this.jjkCharacterService = jjkCharacterService;
        this.apiCounterService = apiCounterService;
    }

    @GetMapping
    public String getAddPage(){
        return "add";
    }
    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

    @GetMapping("/showAll")
    public String showAll(Model model){
        model.addAttribute("characters", jjkCharacterService.findAll());
        return "showAll";
    }

    @PostMapping
    public String addChar(@RequestParam String name,
                          @RequestParam String imgUrl,
                          @RequestParam String gender,
                          @RequestParam String affiliations,
                          @RequestParam String jujutsu,
                          @RequestParam String domain,
                          @RequestParam String energy,
                          @RequestParam String grade,
                          @RequestParam String firstArc) {
        jjkCharacterService.saveCharacter(name, imgUrl, gender, affiliations, jujutsu, domain, energy, grade, firstArc);
        return "redirect:/";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        if(jjkCharacterService.findById(id).isPresent()){
            JjkCharacter character = jjkCharacterService.findById(id).get();

            String aff = String.join(",", character.getAffiliations());
            String jjt = String.join(",", character.getJujutsu());
            String dom = String.join(",", character.getDomain());
            String nrg = String.join(",", character.getEnergy());
            model.addAttribute("jjkCh", character);
            model.addAttribute("aff", aff);
            model.addAttribute("jjt", jjt);
            model.addAttribute("dom", dom);
            model.addAttribute("nrg", nrg);
        }

        return "edit";
    }

    @PostMapping("/edit")
    public String editChar(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam String imgUrl,
                           @RequestParam String gender,
                           @RequestParam String affiliations,
                           @RequestParam String jujutsu,
                           @RequestParam String domain,
                           @RequestParam String energy,
                           @RequestParam String grade,
                           @RequestParam String firstArc){
        jjkCharacterService.editCharacter(id, name, imgUrl, gender, affiliations, jujutsu, domain, energy, grade, firstArc);

        return "redirect:/showAll";
    }
    @GetMapping("/analytics")
    public String getAnalyticsPage(Model model){
        model.addAttribute("todayCount",apiCounterService.getTodayApiCounter());
        model.addAttribute("lifetimeCount",apiCounterService.getTotalApiCounter());
        model.addAttribute("counters",apiCounterService.getAllApiCounter());
        return "analytics";
    }
}
