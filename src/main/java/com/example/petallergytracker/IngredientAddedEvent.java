package com.example.petallergytracker;

import com.example.petallergytracker.Models.Food;
import org.springframework.context.ApplicationEvent;

public class IngredientAddedEvent extends ApplicationEvent {
    private Food food;

    public IngredientAddedEvent(Object source, Food food) {
        super(source);
        this.food = food;
    }

    public Food getIngredient() {
        return food;
    }
}
