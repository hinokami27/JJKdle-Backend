package com.example.jjkdle.web;

import com.example.jjkdle.model.JjkGif;
import com.example.jjkdle.service.JjkGifService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gif")
public class JjkGifController {

    private final JjkGifService gifService;

    public JjkGifController(JjkGifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

    @GetMapping("/allGifs")
    public String getAllGifs(Model model){
        model.addAttribute("gifs", gifService.findAll());
        return "allGifs";
    }

    @GetMapping("/addGif-form")
    public String getAddPage(){
        return "addGif";
    }

    @GetMapping("/editGif-form/{id}")
    public String editGif(@PathVariable Long id, Model model){
        if(gifService.findById(id).isPresent()){
            JjkGif gif = gifService.findById(id).get();

            model.addAttribute("gif", gif);
        }
        return "addGif";
    }

    @PostMapping("/addGif")
    public String addGif(@RequestParam(required = false) Long id,
                         @RequestParam String gifUrl,
                         @RequestParam String characterName,
                         @RequestParam String ability){
        if(id != null){
            gifService.edit(id, gifUrl, characterName, ability);
        }else{
            gifService.save(gifUrl, characterName, ability);
        }

        return "redirect:/gif/allGifs";
    }

}
