package com.example.petallergytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing the user dashboard.
 * Aggregates data from ingredients, reactions, and common allergens to provide a comprehensive overview.
 */
@Service
public class UserDashboardService {

    @Autowired
    private AllergyService allergyService;

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;  // Direct access to repository

    /**
     * Retrieves a list of all ingredients logged by the user.
     * @return List of ingredient names.
     */
    public List<String> getAllIngredients() {
        return allergyService.findAllIngredients().stream()
                             .map(Ingredient::getName)
                             .collect(Collectors.toList());
    }

    /**
     * Retrieves a summary of all allergic reactions recorded.
     * Formats each reaction for display.
     * @return List of formatted allergic reaction strings.
     */
    public List<String> getAllergicReactionsSummary() {
        return allergicReactionRepository.findAll().stream()
                                         .map(reaction -> "Ingredient: " + reaction.getIngredient().getName() +
                                                          ", Symptoms: " + reaction.getSymptoms() +
                                                          ", Severity: " + reaction.getSeverity())
                                         .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of identified common allergens from the allergy service.
     * @return List of common allergens.
     */
    public List<String> getIdentifiedCommonAllergens() {
        return allergyService.identifyCommonAllergens();
    }

    /**
     * Compiles and returns comprehensive dashboard data.
     * This method consolidates various data points for the user dashboard.
     * @return DashboardData containing all relevant information for the user.
     */
    public DashboardData getDashboardData() {
        List<String> ingredients = getAllIngredients();
        List<String> reactions = getAllergicReactionsSummary();
        List<String> commonAllergens = getIdentifiedCommonAllergens();

        return new DashboardData(ingredients, reactions, commonAllergens);
    }
}

/**
 * Simple POJO to hold and structure dashboard data.
 */
class DashboardData {
    private List<String> ingredients;
    private List<String> reactions;
    private List<String> commonAllergens;

    public DashboardData(List<String> ingredients, List<String> reactions, List<String> commonAllergens) {
        this.ingredients = ingredients;
        this.reactions = reactions;
        this.commonAllergens = commonAllergens;
    }

    // Getters
    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getReactions() {
        return reactions;
    }

    public List<String> getCommonAllergens() {
        return commonAllergens;
    }
}
