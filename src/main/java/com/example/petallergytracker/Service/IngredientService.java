package com.example.petallergytracker.Service;

import com.example.petallergytracker.IngredientAddedEvent;
import com.example.petallergytracker.Models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Food addIngredient(Food food) {
        // Assume saving the ingredient to the database
        // repository.save(ingredient);

        // Publish the event
        eventPublisher.publishEvent(new IngredientAddedEvent(this, food));
        return food;
    }

}
