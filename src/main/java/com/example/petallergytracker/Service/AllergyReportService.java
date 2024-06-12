package com.example.petallergytracker.Service;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Repository.AllergicReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class to generate allergy reports based on recorded reactions.
 */
@Service
public class AllergyReportService {

    @Autowired
    private AllergicReactionRepository allergicReactionRepository;

    /**
     * Generates a report summarizing allergic reactions.
     * This method fetches reactions from the database and formats them into a report.
     *
     * @return String representing the formatted report of allergic reactions.
     */
    public String generateAllergyReport() {
        // Fetch all reactions from the repository
        List<AllergicReaction> reactions = allergicReactionRepository.findAll();

        // Building the report
        String report = reactions.stream()
            .map(reaction -> "Ingredient: " + reaction.getIngredient().getName() +
                             ", Symptom: " + reaction.getSymptoms() +
                             ", Severity: " + reaction.getSeverity())
            .collect(Collectors.joining("\n"));

        // Return the formatted report
        return report;
    }
}
