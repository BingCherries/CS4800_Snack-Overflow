//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static List<Ingredient> ingredients = new ArrayList<>();
    private static List<AllergicReaction> reactions = new ArrayList<>();
    private static Map<String, Integer> allergenTracker = new HashMap<>();

    public static void main(String[] args) {
        // Initialize ingredients
        ingredients.add(new Ingredient("Chicken Meal", new String[]{"Chicken", "Preservatives"}));
        ingredients.add(new Ingredient("Beef Meal", new String[]{"Beef", "Colorants"}));

        // Log reactions
        logReaction(new AllergicReaction(ingredients.get(0), "Skin rash", 3));
        logReaction(new AllergicReaction(ingredients.get(1), "Vomiting", 5));

        // Display reactions
        for (AllergicReaction reaction : reactions) {
            System.out.println("Reaction to " + reaction.getIngredient().getName() +
                    ": " + reaction.getSymptoms() + ", Severity: " + reaction.getSeverity());
        }

        // Identify common allergens
        identifyCommonAllergens();

        // Generate a report (console output)
        generateAllergyReport();
    }

    private static void logReaction(AllergicReaction reaction) {
        reactions.add(reaction);
        for (String component : reaction.getIngredient().getComponents()) {
            allergenTracker.put(component, allergenTracker.getOrDefault(component, 0) + reaction.getSeverity());
        }
    }

    private static void identifyCommonAllergens() {
        System.out.println("Identified common allergens based on severity:");
        allergenTracker.forEach((key, value) -> {
            if (value > 5) {  // Threshold for severity to consider it a common allergen
                System.out.println("Component: " + key + ", Total Severity: " + value);
            }
        });
    }

    private static void generateAllergyReport() {
        System.out.println("Allergy Report:");
        for (AllergicReaction reaction : reactions) {
            System.out.println("Ingredient: " + reaction.getIngredient().getName() +
                    ", Reaction: " + reaction.getSymptoms() + ", Severity: " + reaction.getSeverity());
        }
    }
}
