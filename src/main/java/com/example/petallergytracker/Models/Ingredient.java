package com.example.petallergytracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents an ingredient entity in the database.
 * Each ingredient has a unique ID, a name, and a list of components associated with it.
 */
@Document("ingredients")
@Entity  // This annotation specifies that the class is an entity and is mapped to a database table.
@Getter
@Setter
public class Ingredient {

    @Id  // Marks the id field as the primary key of the entity.
//    @GeneratedValue(strategy = GenerationType.AUTO)  // Configures the way of increment of the specified column(field).
    private Long id;  // Unique identifier for each ingredient.

    private String name;  // Name of the ingredient.

//    @ElementCollection  // This annotation is used to define a collection of instances of a basic type or embeddable class.
    private List<String> components;  // List of components that make up the ingredient.

    /**
     * Default constructor for JPA.
     */
    public Ingredient() {}

    /**
     * Constructs a new Ingredient with specified name and components.
     * @param name The name of the ingredient.
     * @param components The components that make up the ingredient.
     */
    public Ingredient(String name, List<String> components) {
        this.name = name;
        this.components = components;
    }
}
