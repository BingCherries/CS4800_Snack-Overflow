package com.example.petallergytracker.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Data
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private List<Pet> pets;

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public void removePet(Pet pet) {
        this.pets.remove(pet);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return user.getId().equals(this.getId()) && user.getName().equals(this.getName()) && user.getPassword().equals(this.getPassword()) && user.getEmail().equals(this.getEmail()) && user.getPets().equals(this.pets);
    }
}
