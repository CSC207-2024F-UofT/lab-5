package entity;

/**
 * Represents the nutritional information for a recipe.
 */
public class Nutrition {
    private final String name;
    private final double amount;
    private final String unit;
    private final double percentOfDailyNeeds;

    public Nutrition(String name, double amount, String unit, double percentDailyNeeds) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.percentOfDailyNeeds = percentDailyNeeds;
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
        return percentOfDailyNeeds;
    }

    @Override
    public String toString() {
        return name + ": " + amount + " " + unit + " (" + percentOfDailyNeeds + "% of daily needs)";
    }
}
