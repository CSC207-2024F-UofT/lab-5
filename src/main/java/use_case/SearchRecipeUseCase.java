package use_case.SearchRecipe;

import data_access.RecipeDAO;
import entity.Recipe;

import java.util.List;

public class SearchRecipeUseCase implements use_case.SearchRecipe.SearchRecipeInputBoundary {
    private final RecipeDAO recipeDAO;
    private final use_case.SearchRecipe.SearchRecipeOutputBoundary presenter;

    public SearchRecipeUseCase(RecipeDAO recipeDAO, use_case.SearchRecipe.SearchRecipeOutputBoundary presenter) {
        this.recipeDAO = recipeDAO;
        this.presenter = presenter;
    }

    @Override
    public void searchRecipes(use_case.SearchRecipe.SearchRecipeInputData inputData) {
        List<String> ingredients = inputData.getIngredients();

        // Fetch recipes from DAO
        List<Recipe> recipes = recipeDAO.getRecipesByIngredients(ingredients);

        // Pass results to presenter
        use_case.SearchRecipe.SearchRecipeOutputData outputData = new use_case.SearchRecipe.SearchRecipeOutputData(recipes);
        presenter.presentRecipes(outputData);
    }
}
