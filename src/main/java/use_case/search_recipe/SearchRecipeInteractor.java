package use_case.search_recipe;

import data_access.RecipeDAO;
import entity.Recipe;

import java.util.List;

public class SearchRecipeInteractor implements SearchRecipeInputBoundary {
    private final SearchRecipeOutputBoundary presenter;
    private final RecipeDAO recipeDAO;

    public SearchRecipeInteractor(SearchRecipeOutputBoundary presenter, RecipeDAO recipeDAO) {
        this.presenter = presenter;
        this.recipeDAO = recipeDAO;
    }

    @Override
    public void searchRecipes(SearchRecipeInputData inputData) {
        // Fetch recipes from DAO
        List<Recipe> recipes = recipeDAO.getRecipesByIngredients(inputData.getIngredients());

        // Prepare output data and pass to presenter
        use_case.search_recipe.SearchRecipeOutputData outputData = new SearchRecipeOutputData(recipes);
        presenter.presentRecipes(outputData);
    }
}
