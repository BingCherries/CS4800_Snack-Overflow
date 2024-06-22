package com.example.petallergytracker.Models;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "allergy-list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergyList {
    @Id
    private ObjectId id;
    private String classfication;
    private String type;
    private String group;
    private String allergy;
}
