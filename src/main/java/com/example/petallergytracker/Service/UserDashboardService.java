package com.example.petallergytracker.Service;

import com.example.petallergytracker.Models.DashboardData;
import com.example.petallergytracker.Models.Food;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
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
    private PetAllergyTrackerService petAllergyTrackerService;

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;  // Direct access to repository

    /**
     * Retrieves a list of all ingredients logged by the user.
     * @return List of ingredient names.
     */
    public List<String> getAllFoods() {
        return petAllergyTrackerService.getAllFoods().stream()
                             .map(Food::getName)
                             .collect(Collectors.toList());
    }

    /**
     * Retrieves a summary of all allergic reactions recorded.
     * Formats each reaction for display.
     * @return List of formatted allergic reaction strings.
     */
    public List<String> getAllergicReactionsSummary() {
        return allergicReactionRepository.findAll().stream()
                                         .map(reaction -> "Food: " + reaction.getFood().getName() +
                                                 ", Ingredients: " + reaction.getFood().getIngredients().toString() +
                                                 ", Symptom: " + reaction.getSymptoms() +
                                                 ", Severity: " + reaction.getSeverity())
                                         .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of identified common allergens from the allergy service.
     * @return List of common allergens.
     */
    public List<String> getIdentifiedCommonAllergens() {
        return petAllergyTrackerService.getCommonAllergens();
    }

    /**
     * Compiles and returns comprehensive dashboard data.
     * This method consolidates various data points for the user dashboard.
     * @return DashboardData containing all relevant information for the user.
     */
    public DashboardData getDashboardData() {
        List<String> foods = getAllFoods();
        List<String> reactions = getAllergicReactionsSummary();
        List<String> commonAllergens = getIdentifiedCommonAllergens();

        return new DashboardData(foods, reactions, commonAllergens);
    }
}

