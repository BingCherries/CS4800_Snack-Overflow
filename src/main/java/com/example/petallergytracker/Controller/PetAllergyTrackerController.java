package com.example.petallergytracker.Controller;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.AllergyList;
import com.example.petallergytracker.Models.Food;
import com.example.petallergytracker.Service.PetAllergyTrackerService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PetAllergyTrackerController {

    @Autowired
    private PetAllergyTrackerService petAllergyTrackerService;

    @GetMapping("/")
    public String setup_api() {
        return "hello, world";
    }

    /*
    * ******** CRUD using foodRepository *********
    * */
    //    HttpStatus.OK -> Created food successfully
    //    HttpStatus.BAD_REQUEST -> Couldn't create food (necessary variables were missing to create Food object)
    //    HttpStatus.INTERNAL_SERVER_ERROR -> Else errors
    @PostMapping("/food")
    public ResponseEntity<Food> createFood(@Valid @RequestBody Food food) {
        Food newFood = petAllergyTrackerService.createFood(food);

        if (newFood != null) {
            return new ResponseEntity<Food>(newFood, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/foods")
//    public ResponseEntity<List<Food>> createFoods(@Valid @RequestBody List<Food> foods) {
//        List<Food> newFoods = petAllergyTrackerService.createFoods(foods);
//
//        if (!newFoods.isEmpty()) {
//            return new ResponseEntity<List<Food>>(newFoods, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    HttpStatus.OK -> Got the food successfully
    //    HttpStatus.NOT_FOUND -> The food was not in the DB
    @GetMapping("/food")
    public ResponseEntity<Food> getFood(@RequestParam ObjectId id) {
        Optional<Food> food = petAllergyTrackerService.getFood(id);
        return food.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //    HttpStatus.OK -> Got all foods in DB Successfully
    //    HttpStatus.NOT_FOUND -> There are no foods in the DB
    @GetMapping("/foods")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = petAllergyTrackerService.getAllFoods();

        if (!foods.isEmpty()) {
            return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    HttpStatus.OK -> Got target foods in DB Successfully
    //    HttpStatus.NOT_FOUND -> Target Foods were not in the DB
    @GetMapping("/foods/byIds")
    public ResponseEntity<List<Food>> getFoods(@RequestParam List<ObjectId> ids) {
        List<Food> foods = petAllergyTrackerService.getFoods(ids);

        if (!foods.isEmpty()) {
            return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    HttpStatus.OK -> Deleted the food in DB Successfully
    //    HttpStatus.NOT_FOUND -> The food was not in the DB
    @DeleteMapping("/food")
    public ResponseEntity<Food> deleteFood(@RequestParam ObjectId id) {
        Optional<Food> deletedFood = petAllergyTrackerService.deleteFood(id);
        return deletedFood.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/foods")
    public void deleteAllFoods() {
        petAllergyTrackerService.deleteAllFoods();
    }


    /*
     * ******** CRUD using AllergyReactionRepository *********
     * */
    //    HttpStatus.OK -> Created allergic reaction successfully
    //    HttpStatus.BAD_REQUEST -> Couldn't create allergic reaction (Necessary variables were missing to create AllergicReaction object)
    //    HttpStatus.INTERNAL_SERVER_ERROR -> Else Error
    @PostMapping("/allergy")
    public ResponseEntity<AllergicReaction> createAllergicReaction(@Valid @RequestBody AllergicReaction allergicReaction) {
        AllergicReaction newAllergicReaction = petAllergyTrackerService.createAllergicReaction(allergicReaction);

        if (newAllergicReaction != null) {
            return new ResponseEntity<AllergicReaction>(newAllergicReaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/allergies")
//    public ResponseEntity<List<AllergicReaction>> createAllergicReactions(@Valid @RequestBody List<AllergicReaction> allergicReactions) {
//        List<AllergicReaction> newAllergicReactions = petAllergyTrackerService.createAllergicReactions(allergicReactions);
//
//        if (!newAllergicReactions.isEmpty()) {
//            return new ResponseEntity<List<AllergicReaction>>(newAllergicReactions, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    HttpStatus.OK -> Got the Allergic Reaction successfully
    //    HttpStatus.NOT_FOUND -> The Allergic Reaction is not in DB
    @GetMapping("/allergy")
    public ResponseEntity<AllergicReaction> getAllergicReaction(@RequestParam ObjectId id) {
        Optional<AllergicReaction> allergicReaction = petAllergyTrackerService.getAllergicReaction(id);
        return allergicReaction.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //    HttpStatus.OK -> Got all Allergic Reactions successfully
    //    HttpStatus.NOT_FOUND -> There were no Allergic Reactions in DB
    @GetMapping("/allergies")
    public ResponseEntity<List<AllergicReaction>> getAllAllergicReactions() {
        List<AllergicReaction> allergicReactions = petAllergyTrackerService.getAllAllergicReactions();

        if (!allergicReactions.isEmpty()) {
            return new ResponseEntity<List<AllergicReaction>>(allergicReactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    HttpStatus.OK -> Got target Allergic Reactions successfully
    //    HttpStatus.NOT_FOUND -> Target Allergic Reactions were not in DB
    @GetMapping("/allergies/byIds")
    public ResponseEntity<List<AllergicReaction>> getAllergicReactions(@RequestParam List<ObjectId> ids) {
        List<AllergicReaction> allergicReactions = petAllergyTrackerService.getAllergicReactions(ids);

        if (!allergicReactions.isEmpty()) {
            return new ResponseEntity<List<AllergicReaction>>(allergicReactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    HttpStatus.OK -> Deleted the Allergic Reaction successfully
    //    HttpStatus.NOT_FOUND -> The Allergic Reaction was not in DB
    @DeleteMapping("/allergy")
    public ResponseEntity<AllergicReaction> deleteAllergicReaction(@RequestParam ObjectId id) {
        Optional<AllergicReaction> deletedAllergyReaction = petAllergyTrackerService.deleteAllergicReaction(id);
        return deletedAllergyReaction.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/allergies")
    public void deleteAllAllergicReactions() {
        petAllergyTrackerService.deleteAllAllergicReactions();
    }

    //    HttpStatus.OK -> Returned the common allergens found
    //    HttpStatus.NOT_FOUND -> Haven't found any common allergens
    @GetMapping("/commonallergens")
    public ResponseEntity<List<String>> getCommonAllergens() {
        List<String> commonAllergens = petAllergyTrackerService.getCommonAllergens();

        if (!commonAllergens.isEmpty()) {
            return new ResponseEntity<List<String>>(commonAllergens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    HttpStatus.OK -> Returned the common allergens in the given food
    //    HttpStatus.NOT_FOUND -> Haven't found any common allergens in the given food
    @GetMapping("/allergyalert")
    public ResponseEntity<List<String>> getAllergyAlert(@RequestParam ObjectId id) {
        List<String> allergyAlert = petAllergyTrackerService.getAllergyAlert(id);

        if (!allergyAlert.isEmpty()) {
            return new ResponseEntity<List<String>>(allergyAlert, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /*
     * ******** CRUD using AllergyListRepository *********
     * */
    @GetMapping("/allergylist")
    public ResponseEntity<List<AllergyList>> getAllergyList() {
        List<AllergyList> allergyList = petAllergyTrackerService.getAllergyList();

        if (!allergyList.isEmpty()) {
            return new ResponseEntity<List<AllergyList>>(allergyList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }




    //    /**
//     * Endpoint to retrieve common allergens based on recorded allergic reactions.
//     * @return A list of common allergens.
//     */
//    @GetMapping("/common-allergens")
//    public ResponseEntity<List<String>> getCommonAllergens() {
//        List<String> commonAllergens = petAllergyTrackerService.identifyCommonAllergens();
//        System.out.println(commonAllergens);
//        return new ResponseEntity<List<String>>(commonAllergens, HttpStatus.OK);
//    }

}
