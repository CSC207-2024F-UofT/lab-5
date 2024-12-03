package interface_adapter.search_recipe_list_by_ingredient;

import entity.User;
import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientInputBoundary;
import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientInputData;

import java.util.List;

/**
 * Controller for the Search Recipe List By Ingredient Use Case.
 */
public class SearchRecipeListByIngredientController {
    private final SearchRecipeListByIngredientInputBoundary searchRecipeListByIngredientUseCaseInteractor;

    public SearchRecipeListByIngredientController(
            SearchRecipeListByIngredientInputBoundary searchRecipeListByIngredientUseCaseInteractor) {
        this.searchRecipeListByIngredientUseCaseInteractor = searchRecipeListByIngredientUseCaseInteractor;
    }

    /**
     * Executes the Search Recipe List By Ingredient Use Case.
     * @param ingredients the ingredients entered by the user.
     * @param user the user.
     * @param folder the folder to search in.
     */
    public void execute(List<String> ingredients, User user, String folder) {
        final SearchRecipeListByIngredientInputData searchRecipeListByIngredientInputData =
                new SearchRecipeListByIngredientInputData(ingredients, user, folder);

        searchRecipeListByIngredientUseCaseInteractor.execute(searchRecipeListByIngredientInputData);
    }
}
