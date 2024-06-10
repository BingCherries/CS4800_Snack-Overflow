public class Ingredient
{
    private String name;
    private String[] components;

    public Ingredient(String name, String[] components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public String[] getComponents() {
        return components;
    }
}
