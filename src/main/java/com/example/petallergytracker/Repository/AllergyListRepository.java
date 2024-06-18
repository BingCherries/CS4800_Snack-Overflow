package com.example.petallergytracker.Repository;

import com.example.petallergytracker.Models.AllergyList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AllergyListRepository extends MongoRepository<AllergyList, Long> {
}
