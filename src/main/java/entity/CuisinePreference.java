package entity;

/**
 * Represents the user's cuisine preference for filtering recipes.
 */
public class CuisinePreference {
    private final String cuisineType;

    public CuisinePreference(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    /**
     * Checks if the recipe matches with the cuisine preference.
     * @param recipe from user
     * @return a boolean value for whether recipe matches cuisine.
     */
    public boolean matchesRecipe(Recipe recipe) {
        return recipe.getCuisineType().equalsIgnoreCase(this.cuisineType);
        // returns a boolean value, ignoring cases but must be spelled right
    }

}
