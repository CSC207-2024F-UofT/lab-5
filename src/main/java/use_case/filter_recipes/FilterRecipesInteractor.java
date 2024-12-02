package use_case.filter_recipes;

import java.util.List;

import entity.Recipe;

/**
 * The Filter Recipes Interactor.
 */
public class FilterRecipesInteractor implements FilterRecipesInputBoundary {

    private final FilterRecipesDataAccessInterface userDataAccessObject;
    private FilterRecipesOutputBoundary outputBoundary;

    public FilterRecipesInteractor(FilterRecipesDataAccessInterface userDataAccessObject,
                                   FilterRecipesOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    public FilterRecipesInteractor(FilterRecipesDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public List<String> getAvailableDiets() {
        return userDataAccessObject.getAvailableDiets();
    }

    @Override
    public List<String> getAvailableCuisines() {
        return userDataAccessObject.getAvailableCuisines();
    }

    @Override
    public void filterSearchRecipes(FilterRecipesInputData filterRecipesInputData) {
        final List<String> ingredients = filterRecipesInputData.getIngredients();
        final String diet = filterRecipesInputData.getDiet();
        final String cuisine = filterRecipesInputData.getCuisine();
        final List<Recipe> filteredRecipes = userDataAccessObject.filterSearchRecipes(ingredients, diet, cuisine);
        final FilterRecipesOutputData filterRecipesOutputData = new FilterRecipesOutputData(filteredRecipes);
        outputBoundary.prepareSuccessView(filterRecipesOutputData);
    }

}
