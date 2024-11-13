package entity;

/**
 * This class represents the criteria used to filter search results.
 */
public class SearchFilter {
    private String cuisineType;

    /**
     * Checks if the recipe matches the cuisine type.
     * @param recipe from user
     * @return a boolean value for whether selected cuisine type matches cuisine type of recipe
     */
    public boolean matchesRecipe(Recipe recipe) {
        return cuisineType.equals(recipe.getCuisineType());
    }
}
