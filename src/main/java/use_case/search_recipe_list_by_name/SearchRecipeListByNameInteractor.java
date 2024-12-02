package use_case.search_recipe_list_by_name;

import entity.Recipe;
import entity.User;

import java.util.List;

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
        String recipeName = searchRecipeListByNameInputData.getRecipeName();
        final User user = searchRecipeListByNameInputData.getUser();
        final String folder = searchRecipeListByNameInputData.getFolder();
        final List<Recipe> recipes = userDataAccessObject.searchRecipeListByName(recipeName, user, folder);

        final SearchRecipeListByNameOutputData searchRecipeListByNameOutputData =
                new SearchRecipeListByNameOutputData(recipes,
                false);
        userPresenter.prepareSuccessView(searchRecipeListByNameOutputData);
    }
}
