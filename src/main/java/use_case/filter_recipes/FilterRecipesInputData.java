package use_case.filter_recipes;

import java.util.List;

import entity.CuisinePreference;
import entity.DietaryPreference;

/**
 * The input data for the filter recipes use case.
 */
public class FilterRecipesInputData {

    private final DietaryPreference dietaryPreference;
    private final CuisinePreference cuisinePreference;
    private final List<String> ingredients;

    public FilterRecipesInputData(DietaryPreference dietaryPreference,
                                  CuisinePreference cuisinePreference, List<String> ingredients) {
        this.dietaryPreference = dietaryPreference;
        this.cuisinePreference = cuisinePreference;
        this.ingredients = ingredients;
    }

    public DietaryPreference getDietaryPreference() {
        return dietaryPreference;
    }

    public CuisinePreference getCuisinePreference() {
        return cuisinePreference;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

}

