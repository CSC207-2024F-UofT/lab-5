package interface_adapter;

import use_case.SearchRecipeUseCase;
import entity.Recipe;
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
