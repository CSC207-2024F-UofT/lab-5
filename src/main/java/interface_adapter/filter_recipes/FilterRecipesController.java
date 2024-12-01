package interface_adapter.filter_recipes;

import java.util.List;

import entity.Recipe;
import use_case.filter_recipes.FilterRecipesInputBoundary;
import use_case.filter_recipes.FilterRecipesInputData;
import use_case.filter_recipes.FilterRecipesInteractor;

/**
 * Controller for the filter recipes use case.
 */
public class FilterRecipesController {
    private FilterRecipesInputBoundary filterRecipesInputBoundary;

    public FilterRecipesController(FilterRecipesInputBoundary filterRecipesInputBoundary) {
        this.filterRecipesInputBoundary = filterRecipesInputBoundary;
    }

    // /**
     // * Executes the filter recipes use case.
     // * @param diet diet choice
     // * @param cuisine cuisine choice
     // * @param ingredients list of ingredients
     // */
    // public void execute(DietaryPreference diet, CuisinePreference cuisine, List<String> ingredients) {
     //    final FilterRecipesInputData filterRecipesInputData = new FilterRecipesInputData(diet, cuisine, ingredients);

     //    filterRecipesUseCaseInteractor.execute(filterRecipesInputData);
    // }

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
