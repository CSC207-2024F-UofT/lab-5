package entity;

/**
 * Represents the user's dietary preferences for filtering recipes.
 */
public class DietaryPreference {
    private boolean isVegetarian;
    private boolean isVegan;
    private boolean isGlutenFree;
    private boolean isKeto;
    // implement allergies too!

    /**
     * Checks if the recipe matches with the dietary preference.
     * @param recipe from user
     * @return a boolean value for whether recipe matches diet.
     */
    public boolean matchesRecipe(Recipe recipe) {
        return true;
        // not yet implemented
    }

}
