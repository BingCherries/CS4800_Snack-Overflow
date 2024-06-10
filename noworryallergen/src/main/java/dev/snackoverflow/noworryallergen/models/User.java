package dev.snackoverflow.noworryallergen.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String name;
    private String password;
    private String email;
    private List<Pet> pets;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public boolean verifyLogin() {
        return false;
    }

    public void addPet() {

    }

    public void removePet() {

    }

    public void updatePet() {

    }



}
