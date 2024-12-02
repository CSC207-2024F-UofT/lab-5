package use_case.search_recipe_list_by_ingredient;

import entity.User;

public class SearchRecipeListByIngredientInteractor implements SearchRecipeListByIngredientInputBoundary {

    /**
     * The Change Password Interactor.
     */
    private final SearchRecipeListByIngredientDataAccessInterface userDataAccessObject;
    private final SearchRecipeListByIngredientOutputBoundary userPresenter;
    private final User user;

    public SearchRecipeListByIngredientInteractor(
            SearchRecipeListByIngredientDataAccessInterface searchRecipeListByIngredientDataAccessInterface,
            SearchRecipeListByIngredientOutputBoundary searchRecipeListByIngredientOutputBoundary) {
        this.userDataAccessObject = searchRecipeListByIngredientDataAccessInterface;
        this.userPresenter = searchRecipeListByIngredientOutputBoundary;
    }

    @Override
    public void execute(SearchRecipeListByIngredientInputData searchRecipeListByIngredientInputData) {
        final User user = new User(searchRecipeListByIngredientInputData.getusername(),
                searchRecipeListByIngredientInputData.getPassword(),
                searchRecipeListByIngredientInputData.get...
        )
        userDataAccessObject.metjod(user);

        final SearchRecipeListByIngredientOutputData searchRecipeListByIngredientOutputData =
                new SearchRecipeListByIngredientOutputData(recipes,
                false);
        userPresenter.prepareSuccessView(searchRecipeListByIngredientOutputData);
    }
}
