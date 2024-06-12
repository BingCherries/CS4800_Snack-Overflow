package com.example.petallergytracker.Service;

import com.example.petallergytracker.Models.Ingredient;
import com.example.petallergytracker.Repository.IngredientRepository;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllergyService {

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;

    @Autowired
    private IngredientRepository ingredientRepository; // Assuming this repository exists

    // Constructor injection is recommended over field injection
    public AllergyService(AllergicReactionRepository allergicReactionRepository, IngredientRepository ingredientRepository) {
        this.allergicReactionRepository = allergicReactionRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Identifies common allergens based on the number of allergic reactions recorded.
     * This method fetches aggregated data from the repository and filters for high frequency allergens.
     * @return A list of allergens that are considered common based on a predefined threshold.
     */
    public List<String> identifyCommonAllergens() {
        List<Object[]> reactionCounts = allergicReactionRepository.countReactionsByIngredient();
        final int threshold = 5; // Threshold can be adjusted based on needs
        return reactionCounts.stream()
                .filter(arr -> (long) arr[1] > threshold) // Filter by threshold
                .map(arr -> (String) arr[0]) // Extract ingredient name
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all ingredients from the database.
     * @return A list of all ingredients.
     */
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }
}
