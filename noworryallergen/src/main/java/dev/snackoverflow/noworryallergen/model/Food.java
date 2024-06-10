package dev.snackoverflow.noworryallergen.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Food {
    private String name;
    private List<String> ingredients;

    public Food(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
}
