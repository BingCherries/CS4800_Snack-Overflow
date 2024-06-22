package com.example.petallergytracker.Repository;

import com.example.petallergytracker.Models.AllergyList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyListRepository extends MongoRepository<AllergyList, ObjectId> {
}
