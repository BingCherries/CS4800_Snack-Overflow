package com.example.petallergytracker.Service;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.AllergyList;
import com.example.petallergytracker.Models.Food;
import com.example.petallergytracker.Repository.AllergyListRepository;
import com.example.petallergytracker.Repository.FoodRepository;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        Food newFood = Food.builder()
                .name(food.getName())
                .ingredients(food.getIngredients())
                .build();
        return foodRepository.save(newFood);
    }

    public List<Food> createFoods(List<Food> foods) {
        List<Food> newFoods = foods.stream()
                .map(food -> Food.builder()
                        .name(food.getName())
                        .ingredients(food.getIngredients())
                        .build())
                .collect(Collectors.toList());

        return foodRepository.saveAll(newFoods);
    }

    public Optional<Food> getFood(ObjectId foodId) {
        return foodRepository.findById(foodId);
    }

    public List<Food> getFoods(List<ObjectId> foodIds) {
        return foodRepository.findAllById(foodIds);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> deleteFood(ObjectId foodId) {
        return foodRepository.deleteFoodById(foodId);
    }

    public void deleteAllFoods() {
        foodRepository.deleteAll();
    }



    /*
     * ****************** Implement CRUD for AllergicReactionRepository **************
     * */
    public AllergicReaction createAllergicReaction(AllergicReaction allergicReaction) {
        /*
        Optional<Food> mostRecentFood = getMostRecentFood();
        if (!mostRecentFood.isPresent()) {
            return null;
        }

        AllergicReaction newAllergicReaction = AllergicReaction.builder()
                .food(mostRecentFood.get()).symptoms(allergicReaction.getSymptoms()).severity(allergicReaction.getSeverity()).build();

        return allergicReactionRepository.save(newAllergicReaction);
         */
        Food food = allergicReaction.getFood();
        if (food.getId() == null || !foodRepository.existsById(food.getId())) {
            food = foodRepository.save(food);
        }

        // Create and save the allergic reaction with the provided food
        AllergicReaction newAllergicReaction = AllergicReaction.builder()
                .food(food)
                .symptoms(allergicReaction.getSymptoms())
                .severity(allergicReaction.getSeverity())
                .build();

        return allergicReactionRepository.save(newAllergicReaction);
    }

//    public List<AllergicReaction> createAllergicReactions(List<AllergicReaction> allergicReactions) {
//        return allergicReactionRepository.saveAll(allergicReactions);
//    }

    public Optional<AllergicReaction> getAllergicReaction(ObjectId allergicReactionId) {
        return allergicReactionRepository.findById(allergicReactionId);
    }

    public List<AllergicReaction> getAllergicReactions(List<ObjectId> allergicReactionIds) {
        return allergicReactionRepository.findAllById(allergicReactionIds);
    }

    public List<AllergicReaction> getAllAllergicReactions() {
        return allergicReactionRepository.findAll();
    }

    public Optional<AllergicReaction> deleteAllergicReaction(ObjectId allergicReactionId) {
        return allergicReactionRepository.deleteAllergicReactionById(allergicReactionId);
    }

    public void deleteAllAllergicReactions() {
        allergicReactionRepository.deleteAll();
    }

    public List<String> getCommonAllergens() {
        final int threshold = 5; // Threshold can be adjusted based on needs
        return allergicReactionRepository.findIngredientsAppearingMoreThan(threshold);
    }

    public List<String> getAllergyAlert(ObjectId id) {
        Optional<Food> food = foodRepository.findById(id);
        if (!food.isPresent()) {
            return null;
        }

        List<String> commonAllergens = getCommonAllergens();
        if (commonAllergens.isEmpty()) {
            return commonAllergens;
        }

        Set<String> commonAllergensSet = new HashSet<>(getCommonAllergens());

        List<String> ingredients = food.get().getIngredients();
        List<String> allergyAlert = new ArrayList<>();

        for (String ingredient : ingredients) {
            if (commonAllergensSet.contains(ingredient)) {
                allergyAlert.add(ingredient);
            }
        }
        return allergyAlert;
    }

    public List<AllergyList> getAllergyList() {return allergyListRepository.findAll();}

    /*
    *
    * inner functions
    *
    * */
    private Optional<Food> getMostRecentFood() {
        return foodRepository.findFirstByOrderByIdDesc();
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
