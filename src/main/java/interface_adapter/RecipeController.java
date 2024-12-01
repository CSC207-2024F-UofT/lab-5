package interface_adapter;

import use_case.SearchRecipe.SearchRecipeInputBoundary;
import use_case.SearchRecipe.SearchRecipeInputData;

import java.util.List;

public class RecipeController {
    private final SearchRecipeInputBoundary searchInteractor;

    public RecipeController(SearchRecipeInputBoundary searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void searchRecipes(List<String> ingredients) {
        SearchRecipeInputData inputData = new SearchRecipeInputData(ingredients);
        searchInteractor.searchRecipes(inputData);
    }
}
