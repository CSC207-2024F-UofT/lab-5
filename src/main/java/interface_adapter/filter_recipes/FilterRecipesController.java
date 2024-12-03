package interface_adapter.filter_recipes;

import java.util.List;

import use_case.filter_recipes.FilterRecipesInputBoundary;
import use_case.filter_recipes.FilterRecipesInputData;

/**
 * Controller for the filter recipes use case.
 */
public class FilterRecipesController {
    private FilterRecipesInputBoundary filterRecipesInputBoundary;

    public FilterRecipesController(FilterRecipesInputBoundary filterRecipesInputBoundary) {
        this.filterRecipesInputBoundary = filterRecipesInputBoundary;
    }

    public List<String> getAvailableDiets() {
        return filterRecipesInputBoundary.getAvailableDiets();
    }

    public List<String> getAvailableCuisines() {
        // return filterRecipesUseCaseInteractor.getAvailableCuisines();
        return filterRecipesInputBoundary.getAvailableCuisines();
    }

    /**
     * Filter searches recipes.
     * @param ingredients list of ingredients
     * @param selectedDiet diet choice
     * @param selectedCuisine cuisine choice
     */
    public void filterSearchRecipes(List<String> ingredients, String selectedDiet, String selectedCuisine) {
        final FilterRecipesInputData filterRecipesInputData =
                new FilterRecipesInputData(ingredients, selectedDiet, selectedCuisine);
        filterRecipesInputBoundary.filterSearchRecipes(filterRecipesInputData);
    }
}
