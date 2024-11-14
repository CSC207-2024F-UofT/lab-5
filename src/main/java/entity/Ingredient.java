package entity;

/**
 * Represents an ingredient from our recipe.
 */
public class Ingredient {
    // Attributes based on API documentation
    private double amount;
    private int id;
    private String image;

    // TODO implement the Measures class
    // private Measures measures;

    private String name;

    // probably don't need these
//    private String original;
//    private String originalName;
//    private String unit;

    public Ingredient(String name) {
        // TODO parse the original JSONArray representation of ingredients into our format
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
