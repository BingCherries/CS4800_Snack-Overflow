package dev.snackoverflow.noworryallergen.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Food {
    private String name;
    private String[] ingredients;

    public Food(String name, String[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
}
