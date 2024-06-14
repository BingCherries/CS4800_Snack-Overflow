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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

@SpringBootTest
@AutoConfigureMockMvc
public class AllergicReactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenNewAllergicReaction_whenSave_thenSuccess() throws Exception {
        Ingredient ingredient = new Ingredient("Food1", Arrays.asList("Milk", "Cheese", "etc"));
        AllergicReaction newAllergicReaction = new AllergicReaction(ingredient, "vomit", 4);

        mockMvc.perform(post("/api/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAllergicReaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredient.name", is("Food1")))
                .andExpect(jsonPath("$.reaction", is("vomit")));
    }

    @Test
    void givenExistingPetName_whenGetAllergiesByPetName_thenSuccess() throws Exception {
        Ingredient ingredient = new Ingredient("Food1", Arrays.asList("Milk", "Cheese", "etc"));
        AllergicReaction newAllergicReaction = new AllergicReaction(ingredient, "vomit", 4);
        allergicReactionRepository.save(newAllergicReaction);

        mockMvc.perform(get("/api/allergies/{petName}", "Buddy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ingredient.name", is("Food1")))
                .andExpect(jsonPath("$[0].reaction", is("vomit")));
    }
}