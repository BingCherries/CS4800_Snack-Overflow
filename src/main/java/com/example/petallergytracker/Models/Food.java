package com.example.petallergytracker.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents an ingredient entity in the database.
 * Each ingredient has a unique ID, a name, and a list of components associated with it.
 */
//@Entity  // This annotation specifies that the class is an entity and is mapped to a database table.
@Document(collection = "food")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Food {
    @Id  // Marks the id field as the primary key of the entity.
//    @GeneratedValue(strategy = GenerationType.AUTO)  // Configures the way of increment of the specified column(field).
    private ObjectId id;  // Unique identifier for each ingredient.

   // @NotNull
   // @NotEmpty
    private String name;  // Name of the ingredient.

//    @ElementCollection  // This annotation is used to define a collection of instances of a basic type or embeddable class.
//    @ElementCollection
    //@NotNull
    //@NotEmpty
    private List<String> ingredients;  // List of components that make up the ingredient.

//    /**
//     * Default constructor for JPA.
//     */
//    public Food() {}

//    /**
//     * Constructs a new Ingredient with specified name and components.
//     * @param name The name of the ingredient.
//     * @param ingredients The components that make up the ingredient.
//     */

    @JsonCreator
    public Food(@JsonProperty("name") String name, @JsonProperty("ingredients") List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Food food = (Food) o;
        return food.getId().equals(this.getId()) && food.getName().equals(this.getName()) && food.getIngredients().equals(this.getIngredients());
    }
}
