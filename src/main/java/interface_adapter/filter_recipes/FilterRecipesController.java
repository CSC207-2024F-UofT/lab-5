package interface_adapter.filter_recipes;

import java.util.List;

import entity.CuisinePreference;
import entity.DietaryPreference;
import entity.Recipe;
import use_case.filter_recipes.FilterRecipesInputBoundary;
import use_case.filter_recipes.FilterRecipesInputData;
import use_case.filter_recipes.FilterRecipesInteractor;

/**
 * Controller for the filter recipes use case.
 */
public class FilterRecipesController {
    private FilterRecipesInputBoundary filterRecipesUseCaseInteractor;
    private FilterRecipesInteractor filterRecipesInteractor;

    // overloading for now
    public FilterRecipesController(FilterRecipesInteractor filterRecipesInteractor) {
        this.filterRecipesInteractor = filterRecipesInteractor;
    }

    public FilterRecipesController(FilterRecipesInputBoundary filterRecipesUseCaseInteractor) {
        this.filterRecipesUseCaseInteractor = filterRecipesUseCaseInteractor;
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
        return filterRecipesInteractor.getAvailableDiets();
    }

    public List<String> getAvailableCuisines() {
        // return filterRecipesUseCaseInteractor.getAvailableCuisines();
        return filterRecipesInteractor.getAvailableCuisines();
    }

    /**
     * Filter searches recipes.
     * @param ingredients list of ingredients
     * @param selectedDiet diet choice
     * @param selectedCuisine cuisine choice
     * @return list of recipes
     */
    public List<Recipe> filterSearchRecipes(List<String> ingredients, String selectedDiet, String selectedCuisine) {
        return filterRecipesInteractor.filterSearchRecipes(ingredients, selectedDiet, selectedCuisine);
    }
}
