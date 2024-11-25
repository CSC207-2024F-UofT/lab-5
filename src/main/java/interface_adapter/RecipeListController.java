package interface_adapter;

import entity.Ingredient;
import entity.Recipe;
import use_case.SearchRecipeListUseCase;

import java.util.List;

public class RecipeListController {
    private final SearchRecipeListUseCase searchRecipeListUseCase;

    public RecipeListController(SearchRecipeListUseCase searchRecipeListUseCase) {
        this.searchRecipeListUseCase = searchRecipeListUseCase;
    }

    public List<Recipe> getRecipes(List<Ingredient> ingredients) {
        return searchRecipeListUseCase.searchRecipes(ingredients);
    }
}
