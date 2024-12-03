package use_case.search_recipe_list_by_ingredient;

import java.util.List;

import entity.Recipe;
import entity.User;

/**
 * The Interactor for the Search Recipe List By Name Use Case.
 */
public class SearchRecipeListByIngredientInteractor implements SearchRecipeListByIngredientInputBoundary {

    /**
     * The Search Recipe List By Ingredient Interactor.
     */
    private final SearchRecipeListByIngredientDataAccessInterface userDataAccessObject;
    private final SearchRecipeListByIngredientOutputBoundary userPresenter;

    public SearchRecipeListByIngredientInteractor(
            SearchRecipeListByIngredientDataAccessInterface searchRecipeListByIngredientDataAccessInterface,
            SearchRecipeListByIngredientOutputBoundary searchRecipeListByIngredientOutputBoundary) {
        this.userDataAccessObject = searchRecipeListByIngredientDataAccessInterface;
        this.userPresenter = searchRecipeListByIngredientOutputBoundary;
    }

    @Override
    public void execute(SearchRecipeListByIngredientInputData searchRecipeListByIngredientInputData) {
        // Obtain the parameters for the search from the input data object
        final List<String> ingredients = searchRecipeListByIngredientInputData.getIngredients();
        final User user = searchRecipeListByIngredientInputData.getUser();
        final String folder = searchRecipeListByIngredientInputData.getFolder();
        // Obtain the results of the search from the userDAO
        final List<Recipe> recipes = userDataAccessObject.searchRecipeListByIngredient(ingredients, user, folder);
        // Put the results in the output data object
        final SearchRecipeListByIngredientOutputData searchRecipeListByIngredientOutputData =
                new SearchRecipeListByIngredientOutputData(recipes,
                false);
        // Pass the output data to the presenter
        userPresenter.prepareSuccessView(searchRecipeListByIngredientOutputData);
    }
}
