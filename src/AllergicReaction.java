public class AllergicReaction {
    private Ingredient ingredient;
    private String symptoms;
    private int severity;

    public AllergicReaction(Ingredient ingredient, String symptoms, int severity) {
        this.ingredient = ingredient;
        this.symptoms = symptoms;
        this.severity = severity;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public String getSymptoms() {
        return this.symptoms;
    }

    public int getSeverity() {
        return this.severity;
    }
}

