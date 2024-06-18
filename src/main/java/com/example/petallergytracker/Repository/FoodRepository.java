package com.example.petallergytracker.Repository;

import com.example.petallergytracker.Models.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends MongoRepository<Food, Long> {
    Optional<Food> deleteFoodById(Long id);
}
