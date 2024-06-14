package com.example.petallergytracker.Repository;

import com.example.petallergytracker.Models.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, Long> {
    Optional<Ingredient> deleteIngredientById(Long id);
}
