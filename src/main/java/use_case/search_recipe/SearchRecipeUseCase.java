package use_case.search_recipe;

import data_access.RecipeDAO;
import entity.Recipe;
import interface_adapter.SearchRecipePresenter;

import java.util.List;

public class SearchRecipeUseCase implements use_case.search_recipe.SearchRecipeInputBoundary {
    private final RecipeDAO recipeDAO;
    private final use_case.search_recipe.SearchRecipeOutputBoundary presenter;

    public SearchRecipeUseCase(RecipeDAO recipeDAO, SearchRecipePresenter presenter) {
        this.recipeDAO = recipeDAO;
        this.presenter = presenter;
    }

    @Override
    public void searchRecipes(SearchRecipeInputData inputData) {
        List<String> ingredients = inputData.getIngredients();

        // Fetch recipes from DAO
        List<Recipe> recipes = recipeDAO.getRecipesByIngredients(ingredients);

        // Pass results to presenter
        use_case.search_recipe.SearchRecipeOutputData outputData = new use_case.search_recipe.SearchRecipeOutputData(recipes);
        presenter.presentRecipes(outputData);
    }
}
