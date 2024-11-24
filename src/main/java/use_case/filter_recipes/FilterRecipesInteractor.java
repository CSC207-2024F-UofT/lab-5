package use_case.filter_recipes;

import java.util.List;

import entity.CuisinePreference;
import entity.DietaryPreference;
import entity.Recipe;

/**
 * The Filter Recipes Interactor.
 */
public class FilterRecipesInteractor implements FilterRecipesInputBoundary {

    private final FilterRecipesDataAccessInterface userDataAccessObject;
    private final FilterRecipesOutputBoundary outputBoundary;

    public FilterRecipesInteractor(FilterRecipesDataAccessInterface userDataAccessObject,
                                   FilterRecipesOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void filterRecipesByDiet(DietaryPreference diet) {
        try {
            final List<Recipe> recipes = userDataAccessObject.filterRecipesByDiet(diet);
            final FilterRecipesOutputData outputData = new FilterRecipesOutputData(recipes);
            outputBoundary.prepareSuccessView(outputData);
        }
        catch (Exception exception) {
            outputBoundary.prepareFailView("Failed to filter recipes by diet: " + exception.getMessage());
        }
    }

    @Override
    public void filterRecipesByCuisine(CuisinePreference cuisine) {
        try {
            final List<Recipe> recipes = userDataAccessObject.filterRecipesByCuisine(cuisine);
            final FilterRecipesOutputData outputData = new FilterRecipesOutputData(recipes);
            outputBoundary.prepareSuccessView(outputData);
        }
        catch (Exception exception) {
            outputBoundary.prepareFailView("Failed to filter recipes by cuisine: "
                    + exception.getMessage());
        }
    }

}
