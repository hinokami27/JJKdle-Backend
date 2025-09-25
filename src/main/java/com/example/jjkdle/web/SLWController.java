package com.example.jjkdle.web;

import com.example.jjkdle.model.SixLetterWord;
import com.example.jjkdle.service.SLWService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/words")
public class SLWController {
    private final SLWService slwService;

    public SLWController(SLWService slwService) {
        this.slwService = slwService;
    }

    @GetMapping()
    public String getAllWords(Model model){
        model.addAttribute("words", slwService.findAll());
        return "showAllWords";
    }

    @GetMapping("/add-form")
    public String getAddPage(){
        return "addWord";
    }

    @PostMapping("/add")
    public String addWord(@RequestParam String word){
        slwService.createWord(word);
        return "redirect:/words";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        if(slwService.findById(id).isPresent()){
            SixLetterWord slw = slwService.findById(id).get();
            model.addAttribute("slw", slw);
        }
        return "editWord";
    }

    @PostMapping("/edit")
    public String editWord(@RequestParam(required = false) Long id,
                           @RequestParam String word){
        slwService.editWord(id, word);
        return "redirect:/words";
    }
}
