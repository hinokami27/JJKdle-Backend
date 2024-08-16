package com.example.jjkdle.service.ServiceImpl;

import com.example.jjkdle.model.CompareResponse;
import com.example.jjkdle.model.JjkCharacter;
import com.example.jjkdle.repository.JjkCharacterRepository;
import com.example.jjkdle.service.JjkCharacterDateService;
import com.example.jjkdle.service.JjkCharacterService;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class JjkCharacterServiceImpl implements JjkCharacterService {

    private final JjkCharacterRepository jjkCharacterRepository;
    private final JjkCharacterDateService characterDateService;
    public JjkCharacterServiceImpl(JjkCharacterRepository jjkCharacterRepository, JjkCharacterDateService characterDateService) {
        this.jjkCharacterRepository = jjkCharacterRepository;
        this.characterDateService = characterDateService;
    }

    @Override
    public void saveCharacter(String name, String imgUrl, String gender, String affiliations, String jujutsu, String domain, String energy, String grade, String firstArc) {
        List<String> aff = Arrays.stream(affiliations.split(",")).map(String::trim).collect(Collectors.toList());
        List<String> jjt = Arrays.stream(jujutsu.split(",")).map(String::trim).collect(Collectors.toList());
        List<String> dom = Arrays.stream(domain.split(",")).map(String::trim).collect(Collectors.toList());
        List<String> nrg = Arrays.stream(energy.split(",")).map(String::trim).collect(Collectors.toList());
        JjkCharacter character = new JjkCharacter(name.trim(),imgUrl,gender.trim(),aff,jjt,dom,nrg,grade.trim(),firstArc.trim());
        jjkCharacterRepository.save(character);
    }

    @Override
    public void editCharacter(Long id, String name, String imgUrl, String gender, String affiliations, String jujutsu, String domain, String energy, String grade, String firstArc) {
        JjkCharacter character = jjkCharacterRepository.findById(id).orElseThrow(RuntimeException::new);

        character.setName(name.trim());
        character.setImgUrl(imgUrl);
        character.setGender(gender.trim());
        List<String> aff = Arrays.stream(affiliations.split(",")).map(String::trim).collect(Collectors.toList());
        character.setAffiliations(aff);
        List<String> jjt = Arrays.stream(jujutsu.split(",")).map(String::trim).collect(Collectors.toList());
        character.setJujutsu(jjt);
        List<String> dom = Arrays.stream(domain.split(",")).map(String::trim).collect(Collectors.toList());
        character.setDomain(dom);
        List<String> nrg = Arrays.stream(energy.split(",")).map(String::trim).collect(Collectors.toList());
        character.setEnergy(nrg);
        character.setGrade(grade.trim());
        character.setFirstArc(firstArc.trim());

        jjkCharacterRepository.save(character);
    }

    @Override
    public List<JjkCharacter> findAll() {
        return jjkCharacterRepository.findAll();
    }

    @Override
    public Optional<JjkCharacter> findById(Long id) {
        return jjkCharacterRepository.findById(id);
    }

    @Override
    public boolean compared(JjkCharacter first, JjkCharacter second) {
        if(first.getName().equals(second.getName())){
            //return false when checking for doubles
            return true;
        }
        List<String> affiliations1 = first.getAffiliations();
        List<String> jujutsu1 = first.getJujutsu();
        List<String> domain1 = first.getDomain();
        List<String> energy1 = first.getEnergy();
        List<String> affiliations2 = second.getAffiliations();
        List<String> jujutsu2 = second.getJujutsu();
        List<String> domain2 = second.getDomain();
        List<String> energy2 = second.getEnergy();
        Collections.sort(affiliations1);
        Collections.sort(jujutsu1);
        Collections.sort(domain1);
        Collections.sort(energy1);
        Collections.sort(affiliations2);
        Collections.sort(jujutsu2);
        Collections.sort(domain2);
        Collections.sort(energy2);

        if(first.getGender().equals(second.getGender())
        && affiliations1.equals(affiliations2)
        && jujutsu1.equals(jujutsu2)
        && domain1.equals(domain2)
        && energy1.equals(energy2)
        && first.getGrade().equals(second.getGrade())
        && first.getFirstArc().equals(second.getFirstArc())){
            return true;
        }
        return false;
    }

    @Override
    public void resetImgLinks() {
        for (JjkCharacter jjk : jjkCharacterRepository.findAll()){
            jjk.setImgUrl("/images/photos/"+jjk.getName()+".jpg");
            jjkCharacterRepository.save(jjk);
        }
    }

    @Override
    public CompareResponse compareWinner(String input) {
        JjkCharacter today = characterDateService.getTodayCharacter();
        JjkCharacter guess = jjkCharacterRepository.findByName(input);

        if(compared(today,guess)){
            return new CompareResponse("greenSquare");
        }

        CompareResponse response = new CompareResponse();
        response.setFirstSquare(genderCompare(today.getGender(),guess.getGender()));
        response.setSecondSquare(listCompare(today.getAffiliations(),guess.getAffiliations()));
        response.setThirdSquare(listCompare(today.getJujutsu(),guess.getJujutsu()));
        response.setFourthSquare(listCompare(today.getDomain(),guess.getDomain()));
        response.setFifthSquare(listCompare(today.getEnergy(),guess.getEnergy()));
        response.setSixthSquare(beforeOrAfter(today.getGrade(), guess.getGrade()));
        response.setSeventhSquare(beforeOrAfter(today.getFirstArc(), guess.getFirstArc()));
        response.setGuessed(false);

        return response;
    }

    public String beforeOrAfter(String today, String guess){
        List<String> gradeTiers = Arrays.asList("None","Grade 4","Grade 3","Grade 2","Semi-Grade 1","Grade 1","Special Grade 1","Special Grade");
        List<String> storyArcs = Arrays.asList("Cursed Child Arc","Fearsome Womb Arc","Vs. Mahito Arc",
                "Kyoto Goodwill Event Arc","Death Painting Arc","Gojo's Past Arc","Shibuya Incident Arc",
                "Itadori's Extermination Arc","Perfect Preparation Arc","Culling Game Arc");
        String response;
        int todayInt;
        int guessInt;
        if(gradeTiers.contains(today)){
            todayInt = gradeTiers.indexOf(today);
            guessInt = gradeTiers.indexOf(guess);
        }
        else {
            todayInt = storyArcs.indexOf(today);
            guessInt = storyArcs.indexOf(guess);
        }
        if(todayInt>guessInt){
            response = "upArrow";
        } else if (todayInt<guessInt) {
            response = "downArrow";
        } else {
            response = "greenSquare";
        }
        return response;
    }
    public String genderCompare(String today, String guess){
        if(today.equals(guess)){
            return "greenSquare";
        }
        return "redSquare";
    }

    public String listCompare(List<String> today, List<String> guess){
        Set<String> todaySet = new HashSet<>(today);
        Set<String> guessSet = new HashSet<>(guess);

        if (todaySet.equals(guessSet)) {
            return "greenSquare";
        } else if (Collections.disjoint(todaySet, guessSet)) {
            return "redSquare";
        } else {
            return "yellowSquare";
        }
    }
}
