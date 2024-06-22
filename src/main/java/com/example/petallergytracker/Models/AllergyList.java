package com.example.petallergytracker.Models;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "allergylist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergyList {
    @Id
    private ObjectId id;
    @Field("Class")
    private String classfication;
    @Field("Type")
    private String type;
    @Field("Group")
    private String group;
    @Field("Food")
    private String food;
    @Field("Allergy")
    private String allergy;
}
