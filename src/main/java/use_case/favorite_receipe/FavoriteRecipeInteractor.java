package use_case.favorite_receipe;

import data_access.FavoriteRecipeDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;

import java.util.Arrays;

/**
 * The FavoriteRecipe Interactor.
 */
public class FavoriteRecipeInteractor implements FavoriteRecipeInputBoundary {
    private final FavoriteRecipeOutputBoundary favoriteRecipePresenter;
    private final FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject;

    public FavoriteRecipeInteractor(FavoriteRecipeOutputBoundary favoriteRecipePresenter,
                                    FavoriteRecipeDataAccessInterface favoriteRecipeDataAccessObject) {
        this.favoriteRecipePresenter = favoriteRecipePresenter;
        this.favoriteRecipeDataAccessObject = favoriteRecipeDataAccessObject;
    }

    @Override
    public void execute(FavoriteRecipeInputData favoriteRecipeInputData) {
        final String username = favoriteRecipeInputData.getUsername();
        final String[] favoriteRecipes = favoriteRecipeInputData.getFavoriteRecipes();
        final User user = favoriteRecipeDataAccessObject.get(username);
        user.setFavoriteRecipes(favoriteRecipes);
        favoriteRecipeDataAccessObject.updateUserFavoriteRecipes(user);
        System.out.println("Current account in InMemoryUserDataAccessObject: " + favoriteRecipeDataAccessObject.get(username).getName());
        System.out.println("Current favoriteRecipes in InMemoryUserDataAccessObject: " + Arrays.toString(favoriteRecipeDataAccessObject.get(username).getFavoriteRecipes()));
    }

    @Override
    public void switchToShoppingListView() {
        favoriteRecipePresenter.switchToShoppingListView();
    }
}
