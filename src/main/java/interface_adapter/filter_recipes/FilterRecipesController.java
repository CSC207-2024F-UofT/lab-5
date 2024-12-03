package interface_adapter.filter_recipes;

import java.util.List;

import use_case.filter_recipes.FilterRecipesInputBoundary;
import use_case.filter_recipes.FilterRecipesInputData;

/**
 * Controller for the filter recipes use case.
 */
public class FilterRecipesController {
    private final FilterRecipesInputBoundary filterRecipesInteractor;

    public FilterRecipesController(FilterRecipesInputBoundary filterRecipesInteractor) {
        this.filterRecipesInteractor = filterRecipesInteractor;
    }


    /**
     * Executes the filter recipes use case.
     * @param ingredients list of ingredients
     * @param selectedDiet chosen diet filter
     * @param selectedCuisine chosen cuisine filter
     */
    public void execute(List<String> ingredients, String selectedDiet, String selectedCuisine) {
        final FilterRecipesInputData filterRecipesInputData =
                new FilterRecipesInputData(ingredients, selectedDiet, selectedCuisine);
        filterRecipesInteractor.filterSearchRecipes(filterRecipesInputData);
    }

//    /**
//     * Filter searches recipes.
//     * @param ingredients list of ingredients
//     * @param selectedDiet diet choice
//     * @param selectedCuisine cuisine choice
//     */
//    public void filterSearchRecipes(List<String> ingredients, String selectedDiet, String selectedCuisine) {
//        final FilterRecipesInputData filterRecipesInputData =
//                new FilterRecipesInputData(ingredients, selectedDiet, selectedCuisine);
//        filterRecipesInputBoundary.filterSearchRecipes(filterRecipesInputData);
//    }
}
