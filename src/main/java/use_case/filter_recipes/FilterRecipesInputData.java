package use_case.filter_recipes;

import entity.CuisinePreference;
import entity.DietaryPreference;

/**
 * The input data for the filter recipes use case.
 */
public class FilterRecipesInputData {

    private final DietaryPreference dietaryPreference;
    private final CuisinePreference cuisinePreference;

    public FilterRecipesInputData(DietaryPreference dietaryPreference, CuisinePreference cuisinePreference) {
        this.dietaryPreference = dietaryPreference;
        this.cuisinePreference = cuisinePreference;
    }

    public DietaryPreference getDietaryPreference() {
        return dietaryPreference;
    }

    public CuisinePreference getCuisinePreference() {
        return cuisinePreference;
    }
}

