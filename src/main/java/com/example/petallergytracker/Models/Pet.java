package com.example.petallergytracker.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Data
@AllArgsConstructor
public class Pet {
    @Id
    private Long id;
    private User owner;
    private String name;
    private List<AllergicReaction> allergicReactions;
    private List<Food> foods;

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void removeFood(Food food) {
        this.foods.remove(food);
    }

    public void addAllergicReaction(AllergicReaction allergicReaction) {
        this.allergicReactions.add(allergicReaction);
    }

    public void removeAllergicReaction(AllergicReaction allergicReaction) {
        this.allergicReactions.remove(allergicReaction);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Pet pet = (Pet) o;
        return pet.getId().equals(this.getId()) && pet.getName().equals(this.getName()) && pet.getOwner().equals(this.getOwner()) && pet.getAllergicReactions().equals(this.getAllergicReactions()) && pet.getFoods().equals(this.getFoods());
    }

}
