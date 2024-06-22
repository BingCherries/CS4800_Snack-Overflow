package com.example.petallergytracker.Service;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.AllergyList;
import com.example.petallergytracker.Models.Food;
import com.example.petallergytracker.Repository.AllergyListRepository;
import com.example.petallergytracker.Repository.FoodRepository;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetAllergyTrackerService {

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;
    @Autowired
    private FoodRepository foodRepository; // Assuming this repository exists
    @Autowired
    private AllergyListRepository allergyListRepository;

    // Constructor injection is recommended over field injection
    public PetAllergyTrackerService(AllergicReactionRepository allergicReactionRepository, FoodRepository foodRepository) {
        this.allergicReactionRepository = allergicReactionRepository;
        this.foodRepository = foodRepository;
    }

    /*
    * ******************** Implement CRUD for IngredientRepository ****************
    *
    * */
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> createFoods(List<Food> foods) {
        return foodRepository.saveAll(foods);
    }

    public Optional<Food> findFood(Long foodId) {
        return foodRepository.findById(foodId);
    }

    public List<Food> findFoods(List<Long> foodIds) {
        return foodRepository.findAllById(foodIds);
    }

    /**
     * Retrieves all ingredients from the database.
     * @return A list of all ingredients.
     */
    public List<Food> findAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> deleteFood(Long foodId) {
        return foodRepository.deleteFoodById(foodId);
    }

    public void deleteAllFoods() {
        foodRepository.deleteAll();
    }



    /*
     * ****************** Implement CRUD for AllergicReactionRepository **************
     * */
    public AllergicReaction createAllergicReaction(AllergicReaction allergicReaction) {
        return allergicReactionRepository.save(allergicReaction);
    }

    public List<AllergicReaction> createAllergicReactions(List<AllergicReaction> allergicReactions) {
        return allergicReactionRepository.saveAll(allergicReactions);
    }

    public Optional<AllergicReaction> getAllergicReaction(Long allergicReactionId) {
        return allergicReactionRepository.findById(allergicReactionId);
    }

    public List<AllergicReaction> getAllergicReactions(List<Long> allergicReactionIds) {
        return allergicReactionRepository.findAllById(allergicReactionIds);
    }

    /**
     * Retrieves all ingredients from the database.
     * @return A list of all ingredients.
     */
    public List<AllergicReaction> getAllAllergicReactions() {
        return allergicReactionRepository.findAll();
    }

    public Optional<AllergicReaction> deleteAllergicReaction(Long allergicReactionId) {
        return allergicReactionRepository.deleteAllergicReactionById(allergicReactionId);
    }

    public void deleteAllAllergicReactions() {
        allergicReactionRepository.deleteAll();
    }

    public List<String> getCommonAllergens() {
        final int threshold = 5; // Threshold can be adjusted based on needs
        return allergicReactionRepository.findIngredientsAppearingMoreThan(threshold);
    }

    public List<AllergyList> getAllergyList() {return allergyListRepository.findAll();}

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
