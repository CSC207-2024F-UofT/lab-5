package use_case.search_recipe_list_by_name;

import java.util.List;

import entity.Recipe;
import entity.User;

/**
 * The Interactor for the Search Recipe List By Name Use Case.
 */
public class SearchRecipeListByNameInteractor implements SearchRecipeListByNameInputBoundary {

    /**
     * The Search Recipe List By Name Interactor.
     */
    private final SearchRecipeListByNameDataAccessInterface userDataAccessObject;
    private final SearchRecipeListByNameOutputBoundary userPresenter;

    public SearchRecipeListByNameInteractor(
            SearchRecipeListByNameDataAccessInterface searchRecipeListByNameDataAccessInterface,
            SearchRecipeListByNameOutputBoundary searchRecipeListByNameOutputBoundary) {
        this.userDataAccessObject = searchRecipeListByNameDataAccessInterface;
        this.userPresenter = searchRecipeListByNameOutputBoundary;
    }

    @Override
    public void execute(SearchRecipeListByNameInputData searchRecipeListByNameInputData) {
        // Obtain the parameters for the search from the input data object
        final String recipeName = searchRecipeListByNameInputData.getRecipeName();
        final User user = searchRecipeListByNameInputData.getUser();
        final String folder = searchRecipeListByNameInputData.getFolder();
        // Obtain the results of the search from the userDAO
        final List<Recipe> recipes = userDataAccessObject.searchRecipeListByName(recipeName, user, folder);
        // Put the results in the output data object
        final SearchRecipeListByNameOutputData searchRecipeListByNameOutputData =
                new SearchRecipeListByNameOutputData(recipes,
                false);
        // Pass the output data to the presenter
        userPresenter.prepareSuccessView(searchRecipeListByNameOutputData);
    }
}
