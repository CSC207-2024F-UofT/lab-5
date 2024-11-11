package entity;

/**
 * Represents the nutritional information for a recipe.
 */
public class Nutrition {
    private final String name;
    private final double amount;
    private final String unit;
    private final double percentDailyNeeds;

    public Nutrition(String name, double amount, String unit, double percentDailyNeeds) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.percentDailyNeeds = percentDailyNeeds;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public double getPercentDailyNeeds() {
        return percentDailyNeeds;
    }

    @Override
    public String toString() {
        return name + ": " + amount + " " + unit + " (" + percentDailyNeeds + "% of daily needs)";
    }
}
