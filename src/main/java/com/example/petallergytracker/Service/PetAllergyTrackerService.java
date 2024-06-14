package com.example.petallergytracker.Service;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.Ingredient;
import com.example.petallergytracker.Repository.IngredientRepository;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetAllergyTrackerService {

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;
    @Autowired
    private IngredientRepository ingredientRepository; // Assuming this repository exists

    // Constructor injection is recommended over field injection
    public PetAllergyTrackerService(AllergicReactionRepository allergicReactionRepository, IngredientRepository ingredientRepository) {
        this.allergicReactionRepository = allergicReactionRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /*
    * ******************** Implement CRUD for IngredientRepository ****************
    *
    * */
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> createIngredients(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }

    public Optional<Ingredient> findIngredient(Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    public List<Ingredient> findIngredients(List<Long> ingredientIds) {
        return ingredientRepository.findAllById(ingredientIds);
    }

    /**
     * Retrieves all ingredients from the database.
     * @return A list of all ingredients.
     */
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> deleteIngredient(Long ingredientId) {
        return ingredientRepository.deleteIngredientById(ingredientId);
    }

    public void deleteAllIngredients() {
        ingredientRepository.deleteAll();
    }



    /*
     * ****************** Implement CRUD for AllergicReactionRepository **************
     * */
    public AllergicReaction createAllergicReaction(AllergicReaction allergicReaction) {
        return allergicReactionRepository.save(allergicReaction);
    }

    public List<AllergicReaction> createAllergicReactions(List<AllergicReaction> allergyReactions) {
        return allergicReactionRepository.saveAll(allergyReactions);
    }

    public Optional<AllergicReaction> findAllergicReaction(Long allergicReactionId) {
        return allergicReactionRepository.findById(allergicReactionId);
    }

    public List<AllergicReaction> findAllergicReactions(List<Long> allergicReactionIds) {
        return allergicReactionRepository.findAllById(allergicReactionIds);
    }

    /**
     * Retrieves all ingredients from the database.
     * @return A list of all ingredients.
     */
    public List<AllergicReaction> findAllAllergicReactions() {
        return allergicReactionRepository.findAll();
    }

    public Optional<AllergicReaction> deleteAllergicReaction(Long allergicReactionId) {
        return allergicReactionRepository.deleteAllergicReactionById(allergicReactionId);
    }

    public void deleteAllAllergicReactions() {
        allergicReactionRepository.deleteAll();
    }

    public List<String> identifyCommonAllergens() {
        final int threshold = 5; // Threshold can be adjusted based on needs
        return allergicReactionRepository.findComponentsAppearingMoreThan(threshold);
    }

    //    /**
//     * Identifies common allergens based on the number of allergic reactions recorded.
//     * This method fetches aggregated data from the repository and filters for high frequency allergens.
//     * @return A list of allergens that are considered common based on a predefined threshold.
//     */
//    public List<String> identifyCommonAllergens() {
//        List<Object[]> reactionCounts = allergicReactionRepository.countReactionsByIngredient();
//        final int threshold = 5; // Threshold can be adjusted based on needs
//        return reactionCounts.stream()
//                .filter(arr -> (long) arr[1] > threshold) // Filter by threshold
//                .map(arr -> (String) arr[0]) // Extract ingredient name
//                .collect(Collectors.toList());
//    }
}
