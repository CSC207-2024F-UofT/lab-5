package interface_adapter;

import entity.Recipe;
import use_case.SearchRecipeUseCase;

import java.util.List;

public class RecipeController {
    private final SearchRecipeUseCase searchRecipeUseCase;

    public RecipeController(SearchRecipeUseCase searchRecipeUseCase) {
        this.searchRecipeUseCase = searchRecipeUseCase;
    }

    public List<Recipe> getRecipes(List<String> ingredients) {
        return searchRecipeUseCase.searchRecipes(ingredients);
    }
}
