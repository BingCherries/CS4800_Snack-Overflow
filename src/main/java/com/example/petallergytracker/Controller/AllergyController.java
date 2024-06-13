package com.example.petallergytracker.Controller;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Service.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AllergyController {

    @Autowired
    private AllergyService allergyService;

    /**
     * Endpoint to retrieve common allergens based on recorded allergic reactions.
     * @return A list of common allergens.
     */
    @GetMapping("/common-allergens")
    public List<String> getCommonAllergens() {
        return allergyService.identifyCommonAllergens();
    }

    @GetMapping("/")
    public String setup_api() {
        System.out.println("reached allergy controller!");
        return "hello, world";
    }

// testing allergic reaction repo
    @GetMapping("/allergies")
    public List<AllergicReaction> getAllAllergyReactions() {
        return allergyService.AllergicReactionRepositoryFindAll();
    }

    @PostMapping("/allergy")
    public ResponseEntity<AllergicReaction> createAllergyReaction(@RequestBody AllergicReaction allergicReaction) {
        return new ResponseEntity<AllergicReaction>(allergyService.createAllergyReaction(allergicReaction), HttpStatus.CREATED);
    }

}
