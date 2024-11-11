package data_access;

import entity.Recipe;

import java.util.List;

public interface RecipeDAO {
    List<Recipe> getRecipesByIngredients(List<String> ingredients);

    List<Recipe> getAllRecipes();
}

