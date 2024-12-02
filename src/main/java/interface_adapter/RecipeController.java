package interface_adapter;

import use_case.SearchRecipe.SearchRecipeInputBoundary;
import use_case.search_recipe.SearchRecipeInputData;
import use_case.search_recipe.SearchRecipeUseCase;

import java.util.List;

public class RecipeController {
    private final SearchRecipeInputBoundary searchInteractor;

    public RecipeController(SearchRecipeUseCase searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void searchRecipes(List<String> ingredients) {
        SearchRecipeInputData inputData = new SearchRecipeInputData(ingredients);
        searchInteractor.searchRecipes(inputData);
    }
}
