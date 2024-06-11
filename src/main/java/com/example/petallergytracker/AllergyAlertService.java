package com.example.petallergytracker;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AllergyAlertService {

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;

    /**
     * Listens for IngredientAdded events and checks if the ingredient includes any common allergens.
     * Alerts are sent if potential allergens are found.
     *
     * @param event the ingredient added event containing the new ingredient details.
     */
    @EventListener
    public void onIngredientAdded(IngredientAddedEvent event) {
        Ingredient newIngredient = event.getIngredient();
        // Check against known allergens (simplified logic here)
        boolean containsAllergens = allergicReactionRepository.findAll().stream()
            .anyMatch(reaction -> reaction.getIngredient().equals(newIngredient));

        if (containsAllergens) {
            // Logic to send real-time alert
            System.out.println("Alert: New ingredient contains known allergens!");
        }
    }
}
