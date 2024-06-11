package com.example.petallergytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
