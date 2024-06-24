package com.example.petallergytracker;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Models.Food;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;


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
        Food food = new Food("Food1", Arrays.asList("Milk", "Cheese", "etc"));
        AllergicReaction newAllergicReaction = new AllergicReaction(food, "vomit", 4);

        mockMvc.perform(post("/api/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAllergicReaction)))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.ingredient.name", is("Food1")))
                .andExpect(jsonPath("$.food.name", is("Food1")))
                .andExpect(jsonPath("$.food.ingredients[0]", is("Milk")))
                .andExpect(jsonPath("$.food.ingredients[1]", is("Cheese")))
                .andExpect(jsonPath("$.food.ingredients[2]", is("etc")))
                //.andExpect(jsonPath("$.reaction", is("vomit")));
                .andExpect(jsonPath("$.symptoms", is("vomit")))
                .andExpect(jsonPath("$.severity", is(4)));
    }

    @Test
    void givenExistingPetName_whenGetAllergiesByPetName_thenSuccess() throws Exception {
        Food food = new Food("Food1", Arrays.asList("Milk", "Cheese", "etc"));
        AllergicReaction newAllergicReaction = new AllergicReaction(food, "vomit", 4);
        allergicReactionRepository.save(newAllergicReaction);

        mockMvc.perform(get("/api/allergies/{petName}", "Buddy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$[0].ingredient.name", is("Food1")))
                //.andExpect(jsonPath("$[0].reaction", is("vomit")));
                .andExpect(jsonPath("$.food.name", is("Food1")))
                .andExpect(jsonPath("$.food.ingredients[0]", is("Milk")))
                .andExpect(jsonPath("$.food.ingredients[1]", is("Cheese")))
                .andExpect(jsonPath("$.food.ingredients[2]", is("etc")))
                .andExpect(jsonPath("$.symptoms", is("vomit")))
                .andExpect(jsonPath("$.severity", is(4)));
    }
}