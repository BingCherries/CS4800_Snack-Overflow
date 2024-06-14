package com.example.petallergytracker.Controller;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.Ingredient;
import com.example.petallergytracker.Service.IngredientService;
import com.example.petallergytracker.Service.PetAllergyTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PetAllergyTrackerController {

    @Autowired
    private PetAllergyTrackerService petAllergyTrackerService;

    @GetMapping("/")
    public String setup_api() {
        return "hello, world";
    }

    /*
    * ******** CRUD for IngredientRepository *********
    * */
    @PostMapping("/ingredient")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = petAllergyTrackerService.createIngredient(ingredient);

        if (newIngredient != null) {
            return new ResponseEntity<Ingredient>(newIngredient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> createIngredients(@RequestBody List<Ingredient> ingredients) {
        List<Ingredient> newIngredients = petAllergyTrackerService.createIngredients(ingredients);

        if (!newIngredients.isEmpty()) {
            return new ResponseEntity<List<Ingredient>>(newIngredients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ingredient")
    public ResponseEntity<Ingredient> findIngredient(@RequestParam Long id) {
        Optional<Ingredient> ingredient = petAllergyTrackerService.findIngredient(id);
        return ingredient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> findAllIngredients() {
        List<Ingredient> Ingredients = petAllergyTrackerService.findAllIngredients();

        if (!Ingredients.isEmpty()) {
            return new ResponseEntity<List<Ingredient>>(Ingredients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ingredients/byIds")
    public ResponseEntity<List<Ingredient>> findIngredients(@RequestParam List<Long> ids) {
        List<Ingredient> Ingredients = petAllergyTrackerService.findIngredients(ids);

        if (!Ingredients.isEmpty()) {
            return new ResponseEntity<List<Ingredient>>(Ingredients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ingredient")
    public ResponseEntity<Ingredient> deleteIngredient(@RequestParam Long id) {
        Optional<Ingredient> deletedIngredient = petAllergyTrackerService.deleteIngredient(id);
        return deletedIngredient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/ingredients")
    public void deleteAllIngredients() {
        petAllergyTrackerService.deleteAllIngredients();
    }


    /*
     * ******** CRUD for AllergyReactionRepository *********
     * */
    @PostMapping("/allergy")
    public ResponseEntity<AllergicReaction> createAllergyReaction(@RequestBody AllergicReaction allergicReaction) {
        AllergicReaction newAllergicReaction = petAllergyTrackerService.createAllergicReaction(allergicReaction);

        if (newAllergicReaction != null) {
            return new ResponseEntity<AllergicReaction>(newAllergicReaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/allergies")
    public ResponseEntity<List<AllergicReaction>> createAllergyReactions(@RequestBody List<AllergicReaction> allergicReactions) {
        List<AllergicReaction> newAllergicReactions = petAllergyTrackerService.createAllergicReactions(allergicReactions);

        if (!newAllergicReactions.isEmpty()) {
            return new ResponseEntity<List<AllergicReaction>>(newAllergicReactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allergy")
    public ResponseEntity<AllergicReaction> findAllergyReaction(@RequestParam Long id) {
        Optional<AllergicReaction> allergicReaction = petAllergyTrackerService.findAllergicReaction(id);
        return allergicReaction.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/allergies")
    public ResponseEntity<List<AllergicReaction>> findAllAllergyReactions() {
        List<AllergicReaction> allergicReactions = petAllergyTrackerService.findAllAllergicReaction();

        if (!allergicReactions.isEmpty()) {
            return new ResponseEntity<List<AllergicReaction>>(allergicReactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allergies/byIds")
    public ResponseEntity<List<AllergicReaction>> findAllergyReactions(@RequestParam List<Long> ids) {
        List<AllergicReaction> allergicReactions = petAllergyTrackerService.findAllergicReactions(ids);

        if (!allergicReactions.isEmpty()) {
            return new ResponseEntity<List<AllergicReaction>>(allergicReactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/allergy")
    public ResponseEntity<AllergicReaction> deleteAllergyReaction(@RequestParam Long id) {
        Optional<AllergicReaction> deletedAllergyReaction = petAllergyTrackerService.deleteAllergicReaction(id);
        return deletedAllergyReaction.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/allergies")
    public void deleteAllAllergyReactions() {
        petAllergyTrackerService.deleteAllAllergicReaction();
    }

    /**
     * Endpoint to retrieve common allergens based on recorded allergic reactions.
     * @return A list of common allergens.
     */
    @GetMapping("/common-allergens")
    public ResponseEntity<List<String>> getCommonAllergens() {
        List<String> commonAllergens = petAllergyTrackerService.identifyCommonAllergens();
        System.out.println(commonAllergens);
        return new ResponseEntity<List<String>>(commonAllergens, HttpStatus.OK);
    }

}
