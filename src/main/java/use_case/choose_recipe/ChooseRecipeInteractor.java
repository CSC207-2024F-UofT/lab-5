package use_case.choose_recipe;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

import java.util.Arrays;

/**
 * The Choose Recipe Interactor.
 * 从controller那准备好的input data知道哪个菜的名字
 * 从DAO里找outputdata，菜的ingredients, instructions
 * 把outputdata 准备好，也是哪个菜的具体信息
 * 知道在什么情况下准备什么view
 * successful view就让presenter拿着这些data换页面之类的
 */

public class ChooseRecipeInteractor implements ChooseRecipeInputBoundary {
    private final ChooseRecipeOutputBoundary chooseRecipePresenter;
    private final ChooseRecipeDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded = false;  // Flag to ensure loading from cloud only once

    public ChooseRecipeInteractor(ChooseRecipeDataAccessInterface chooseRecipeDataAccessInterface,
                                  ChooseRecipeOutputBoundary chooseRecipePresenter) {
        this.chooseRecipePresenter = chooseRecipePresenter;
        this.recipeDataAccessObject = chooseRecipeDataAccessInterface;
    }

    @Override
    public void execute(ChooseRecipeInputData chooseRecipeInputData) {
        // Ensure recipes are loaded from the cloud only once
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud for the first time...");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }

        final String username = chooseRecipeInputData.getUsername();
        final String[] favoriteRecipes = chooseRecipeInputData.getFavoriteRecipes();
        System.out.println("Current logged in account: " + username);
        System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));

        final String searchDishName = chooseRecipeInputData.getDishName();

        final Recipe recipe = recipeDataAccessObject.getOneRecipe(searchDishName);

        final ChooseRecipeOutputData chooseRecipeOutputData = new ChooseRecipeOutputData(
                recipe.getName(), recipe.getIngredients(), recipe.getInstructions(), recipe.getLikeNumber(), username, favoriteRecipes);
        chooseRecipePresenter.prepareSuccessView(chooseRecipeOutputData);
    }
}