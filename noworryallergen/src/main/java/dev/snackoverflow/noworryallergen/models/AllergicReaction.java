package dev.snackoverflow.noworryallergen.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllergicReaction {
    private Food food;
    private String symptoms;
    private int severity;

    public AllergicReaction(Food food, String symptoms, int severity) {
        this.food = food;
        this.symptoms = symptoms;
        this.severity = severity;
    }
}
