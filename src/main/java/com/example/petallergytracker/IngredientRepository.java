package com.example.petallergytracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // Basic CRUD operations are already provided by JpaRepository
}
