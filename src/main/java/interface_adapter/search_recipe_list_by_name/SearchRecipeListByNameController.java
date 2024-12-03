package interface_adapter.search_recipe_list_by_name;

import entity.User;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameInputBoundary;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameInputData;

import java.util.List;

/**
 * Controller for the Search Recipe List By Name Use Case.
 */
public class SearchRecipeListByNameController {
    private final SearchRecipeListByNameInputBoundary searchRecipeListByNameUseCaseInteractor;

    public SearchRecipeListByNameController(
            SearchRecipeListByNameInputBoundary searchRecipeListByNameUseCaseInteractor) {
        this.searchRecipeListByNameUseCaseInteractor = searchRecipeListByNameUseCaseInteractor;
    }

    /**
     * Executes the Search Recipe List By Name Use Case.
     * @param recipeName the recipe name entered by the user.
     * @param user the user.
     * @param folder the folder to search in.
     */
    public void execute(String recipeName, User user, String folder) {
        final SearchRecipeListByNameInputData searchRecipeListByNameInputData =
                new SearchRecipeListByNameInputData(recipeName, user, folder);

        searchRecipeListByNameUseCaseInteractor.execute(searchRecipeListByNameInputData);
    }
}
