import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
    private static List<Ingredient> ingredients = new ArrayList<>();
    private static List<AllergicReaction> reactions = new ArrayList<>();
    private static Map<String, Integer> allergenTracker = new HashMap<>();


    public static void main(String[] args) {
        ingredients.add(new Ingredient("Chicken Meal", new String[]{"Chicken", "Preservatives"}));
        ingredients.add(new Ingredient("Beef Meal", new String[]{"Beef", "Colorants"}));
        logReaction(new AllergicReaction(ingredients.get(0), "Skin rash", 3));
        logReaction(new AllergicReaction(ingredients.get(1), "Vomiting", 5));

        for (AllergicReaction reaction : reactions) {
            System.out.println("Reaction to " + reaction.getIngredient().getName() +
                               ": " + reaction.getSymptoms() + ", Severity: " + reaction.getSeverity());
        }

        identifyCommonAllergens();
        generateAllergyReport();
    }

    private static void logReaction(AllergicReaction reaction) {
        reactions.add(reaction);
        String[] components = reaction.getIngredient().getComponents();

        for (String component : components) {
            int currentSeverity = allergenTracker.getOrDefault(component, 0);
            allergenTracker.put(component, currentSeverity + reaction.getSeverity());
        }
    }

    private static void identifyCommonAllergens() {
        System.out.println("Identified common allergens based on severity:");
        allergenTracker.forEach((component, severity) -> {
            if (severity > 5) {
                System.out.println("Component: " + component + ", Total Severity: " + severity);
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
