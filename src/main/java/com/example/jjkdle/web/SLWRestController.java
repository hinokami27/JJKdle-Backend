package com.example.jjkdle.web;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.SixLetterWord;
import com.example.jjkdle.model.SixLetterWordDate;
import com.example.jjkdle.service.SLWDateService;
import com.example.jjkdle.service.SLWService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3000/","http://localhost:5173/","http://localhost:5173","https://jj-kdle-frontend.vercel.app","https://jj-kdle-frontend.vercel.app/","https://www.jjkdle.com/","https://www.jjkdle.com/","https://r6dle-one.vercel.app/","https://r6dle-one.vercel.app","https://jj-kdle-frontend-next.vercel.app","https://jj-kdle-frontend-next.vercel.app/",})
public class SLWRestController {
    private final SLWService slwService;
    private final SLWDateService slwDateService;

    public SLWRestController(SLWService slwService, SLWDateService slwDateService) {
        this.slwService = slwService;
        this.slwDateService = slwDateService;
    }

    @GetMapping("/show")
    public List<SixLetterWord> getAllWords(){
        return slwService.findAll();
    }

    @PostMapping("/guess")
    public CompareResponse guessWord(@RequestParam String word){
        return slwService.returnMatchingCharacters(word);
    }

    @GetMapping("/shuffle")
    public void shuffle(){
        slwDateService.shuffleAndSetDates();
    }

    @GetMapping("/today")
    public SixLetterWord getTodaySlw(){
        return slwDateService.getTodaySlw();
    }

    @GetMapping("/dates")
    public List<SixLetterWordDate> getAllWordDates(){
        return slwDateService.findAll();
    }
}
