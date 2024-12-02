package use_case.search_recipe_list_by_ingredient;

import entity.Recipe;
import entity.User;

import java.util.List;

public class SearchRecipeListByIngredientInteractor implements SearchRecipeListByIngredientInputBoundary {

    /**
     * The Change Password Interactor.
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
        final List<String> ingredients = searchRecipeListByIngredientInputData.getIngredients();
        final User user = searchRecipeListByIngredientInputData.getUser();
        final String folder = searchRecipeListByIngredientInputData.getFolder();
        final List<Recipe> recipes = userDataAccessObject.searchRecipeListByIngredient(ingredients, user, folder);

        final SearchRecipeListByIngredientOutputData searchRecipeListByIngredientOutputData =
                new SearchRecipeListByIngredientOutputData(recipes,
                false);
        userPresenter.prepareSuccessView(searchRecipeListByIngredientOutputData);
    }
}
