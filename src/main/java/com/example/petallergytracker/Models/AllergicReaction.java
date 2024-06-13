package com.example.petallergytracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing an allergic reaction to a specific ingredient.
 * Stores details about the reaction including symptoms, severity, and the ingredient involved.
 */
@Entity
@Getter
@Setter
public class AllergicReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne  // Many reactions can be associated with one ingredient
    @JoinColumn(name = "ingredient_id", nullable = false)  // Foreign key to Ingredient table
    private Ingredient ingredient;

    @Column(length = 500)
    private String symptoms;  // Description of the allergic symptoms

    private int severity;  // Severity of the reaction, scaled 1-10

    /**
     * Default constructor for JPA.
     */
    public AllergicReaction() {}

    /**
     * Constructs a new Allergic Reaction with specified ingredient, symptoms, and severity.
     * @param ingredient The ingredient that caused the reaction.
     * @param symptoms Descriptive symptoms of the allergic reaction.
     * @param severity Severity level of the reaction.
     */
    public AllergicReaction(Ingredient ingredient, String symptoms, int severity) {
        this.ingredient = ingredient;
        this.symptoms = symptoms;
        this.severity = severity;
    }
}

