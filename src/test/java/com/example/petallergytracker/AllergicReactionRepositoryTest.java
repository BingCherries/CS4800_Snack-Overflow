package com.example.petallergytracker;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.Food;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//@DataJpaTest
@DataMongoTest
public class AllergicReactionRepositoryTest {
    @Autowired
    AllergicReactionRepository allergicReactionRepository;

    //@Autowired
    //TestEntityManager entityManager;

    @Test
    void givenNewAllergicReaction_whenSave_thenSuccess() {
        Food food = new Food("Food1", Arrays.asList("Milk", "Cheese", "etc"));
        AllergicReaction newAllergicReaction = new AllergicReaction(food, "vomit", 4);
        AllergicReaction insertedAllergicReaction = allergicReactionRepository.save(newAllergicReaction);
        //assertThat(entityManager.find(AllergicReaction.class, insertedAllergicReaction.getId())).isEqualTo(newAllergicReaction);
        assertThat(allergicReactionRepository.findById(insertedAllergicReaction.getId()))
                .isPresent()
                .get()
                .satisfies(reaction -> {
                    assertThat(reaction.getFood().getName()).isEqualTo("Food1");
                    //assertThat(reaction.getSymptoms()).isEqualTo("vomit");
                    assertThat(reaction.getFood().getIngredients()).containsExactly("Milk", "Cheese", "etc");
                    assertThat(reaction.getSymptoms()).isEqualTo("vomit");
                    assertThat(reaction.getSeverity()).isEqualTo(4);
                });
    }


}
