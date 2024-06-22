package com.example.petallergytracker.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity representing an allergic reaction to a specific ingredient.
 * Stores details about the reaction including symptoms, severity, and the ingredient involved.
 */
@Document(collection = "allergyreactions")
@Entity
@Data
public class AllergicReaction {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL)  // Many reactions can be associated with one ingredient
//    @JoinColumn(name = "ingredient_id", nullable = false)  // Foreign key to Ingredient table
    private Food food;
//
//    @Column(length = 500)
    private String symptoms;  // Description of the allergic symptoms

    private int severity;  // Severity of the reaction, scaled 1-10

    /**
     * Default constructor for JPA.
     */
    public AllergicReaction() {}

    /**
     * Constructs a new Allergic Reaction with specified ingredient, symptoms, and severity.
     * @param food The ingredient that caused the reaction.
     * @param symptoms Descriptive symptoms of the allergic reaction.
     * @param severity Severity level of the reaction.
     */
    public AllergicReaction(Food food, String symptoms, int severity) {
        this.food = food;
        this.symptoms = symptoms;
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        AllergicReaction allergicReaction = (AllergicReaction) o;
        return allergicReaction.getId().equals(this.getId()) && allergicReaction.getFood().equals(this.getFood()) && allergicReaction.getSymptoms().equals(this.getSymptoms()) && allergicReaction.getSeverity() == this.getSeverity();
    }
}

