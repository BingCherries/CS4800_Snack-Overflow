package com.example.petallergytracker;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.Ingredient;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AllergicReactionRepositoryTest {
    @Autowired
    AllergicReactionRepository allergicReactionRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenNewAllergicReaction_whenSave_thenSuccess() {
        Ingredient ingredient = new Ingredient("Food1", Arrays.asList("Milk", "Cheese", "etc"));
        AllergicReaction newAllergicReaction = new AllergicReaction(ingredient, "vomit", 4);
        AllergicReaction insertedAllergicReaction = allergicReactionRepository.save(newAllergicReaction);
        assertThat(entityManager.find(AllergicReaction.class, insertedAllergicReaction.getId())).isEqualTo(newAllergicReaction);
    }


}
