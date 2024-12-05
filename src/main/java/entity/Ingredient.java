package entity;

/**
 * Represents an ingredient from our recipe.
 */
public class Ingredient {
    private final double amount;
    private final String unit;
    private final String name;

    public Ingredient(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    // getters (no need for setters)
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

}
