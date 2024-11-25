package interface_adapter.filter_recipes;

import entity.CuisinePreference;
import entity.DietaryPreference;
import use_case.filter_recipes.FilterRecipesInputBoundary;
import use_case.filter_recipes.FilterRecipesInputData;

import java.util.List;

/**
 * Controller for the filter recipes use case.
 */
public class FilterRecipesController {
    private final FilterRecipesInputBoundary filterRecipesUseCaseInteractor;

    public FilterRecipesController(FilterRecipesInputBoundary filterRecipesInputBoundary) {
        this.filterRecipesUseCaseInteractor = filterRecipesInputBoundary;
    }

    /**
     * Executes the filter recipes use case.
     * @param diet diet choice
     * @param cuisine cuisine choice
     * @param ingredients list of ingredients
     */
    public void execute(DietaryPreference diet, CuisinePreference cuisine, List<String> ingredients) {
        final FilterRecipesInputData filterRecipesInputData = new FilterRecipesInputData(diet, cuisine, ingredients);

        filterRecipesUseCaseInteractor.execute(filterRecipesInputData);
    }

}
