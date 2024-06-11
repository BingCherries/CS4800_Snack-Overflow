package com.example.petallergytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Ingredient addIngredient(Ingredient ingredient) {
        // Assume saving the ingredient to the database
        // repository.save(ingredient);

        // Publish the event
        eventPublisher.publishEvent(new IngredientAddedEvent(this, ingredient));
        return ingredient;
    }
}
