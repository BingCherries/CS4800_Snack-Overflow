package com.example.petallergytracker.Repository;

import com.example.petallergytracker.Models.Food;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends MongoRepository<Food, ObjectId> {
    Optional<Food> deleteFoodById(ObjectId id);

    @Aggregation(pipeline = {
            "{ '$sort' : { '_id' : -1 } }",
            "{ '$limit' : 1 }"
    })
    Optional<Food> findFirstByOrderByIdDesc();
}
