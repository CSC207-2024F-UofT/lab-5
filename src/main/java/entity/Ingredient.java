package entity;

/**
 * Represents an ingredient from our recipe.
 */
public class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
