package com.example.petallergytracker;

import org.springframework.context.ApplicationEvent;

public class IngredientAddedEvent extends ApplicationEvent {
    private Ingredient ingredient;

    public IngredientAddedEvent(Object source, Ingredient ingredient) {
        super(source);
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
