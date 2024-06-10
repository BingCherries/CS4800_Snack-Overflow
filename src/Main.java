import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    private static List<Ingredient> ingredients = new ArrayList();
    private static List<AllergicReaction> reactions = new ArrayList();
    private static Map<String, Integer> allergenTracker = new HashMap();

    public Main() {
    }

    public static void main(String[] args) {
        ingredients.add(new Ingredient("Chicken Meal", new String[]{"Chicken", "Preservatives"}));
        ingredients.add(new Ingredient("Beef Meal", new String[]{"Beef", "Colorants"}));
        logReaction(new AllergicReaction((Ingredient)ingredients.get(0), "Skin rash", 3));
        logReaction(new AllergicReaction((Ingredient)ingredients.get(1), "Vomiting", 5));
        Iterator var1 = reactions.iterator();

        while(var1.hasNext()) {
            AllergicReaction reaction = (AllergicReaction)var1.next();
            PrintStream var10000 = System.out;
            String var10001 = reaction.getIngredient().getName();
            var10000.println("Reaction to " + var10001 + ": " + reaction.getSymptoms() + ", Severity: " + reaction.getSeverity());
        }

        identifyCommonAllergens();
        generateAllergyReport();
    }

    private static void logReaction(AllergicReaction reaction) {
        reactions.add(reaction);
        String[] var1 = reaction.getIngredient().getComponents();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String component = var1[var3];
            allergenTracker.put(component, (Integer)allergenTracker.getOrDefault(component, 0) + reaction.getSeverity());
        }

    }

    private static void identifyCommonAllergens() {
        System.out.println("Identified common allergens based on severity:");
        allergenTracker.forEach((key, value) -> {
            if (value > 5) {
                System.out.println("Component: " + key + ", Total Severity: " + value);
            }

        });
    }

    private static void generateAllergyReport() {
        System.out.println("Allergy Report:");
        Iterator var0 = reactions.iterator();

        while(var0.hasNext()) {
            AllergicReaction reaction = (AllergicReaction)var0.next();
            PrintStream var10000 = System.out;
            String var10001 = reaction.getIngredient().getName();
            var10000.println("Ingredient: " + var10001 + ", Reaction: " + reaction.getSymptoms() + ", Severity: " + reaction.getSeverity());
        }

    }
}
