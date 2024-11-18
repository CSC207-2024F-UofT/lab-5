package entity;

/**
 * This class represents the criteria used to filter search results.
 */
public class SearchFilter {
    private final DietaryPreference dietPref;
    private final CuisinePreference cuisinePref;

    public SearchFilter(DietaryPreference dietPref, CuisinePreference cuisinePref) {
        this.dietPref = dietPref;
        this.cuisinePref = cuisinePref;
    }

    public DietaryPreference getDietPreference() {
        return dietPref;
    }

    public CuisinePreference getCuisinePreference() {
        return cuisinePref;
    }

    /**
     * Checks if the recipe matches the cuisine type.
     * @param recipe from user
     * @return a boolean value for whether selected cuisine type matches cuisine type of recipe
     */
    public boolean matchesDietAndCuisine(Recipe recipe) {
        boolean result = true;
        if (dietPref != null) {
            result = dietPref.matchesRecipe(recipe);
            // result is False if recipe doesn't match dietary preference
            // result stays True if it does match
        }

        if (cuisinePref != null) {
            result = cuisinePref.matchesRecipe(recipe);
            // result is False if recipe doesn't match cuisine preference
        }
        return result;
    }
}
