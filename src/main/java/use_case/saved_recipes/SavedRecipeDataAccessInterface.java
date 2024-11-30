package use_case.saved_recipes;

import entity.Recipe;

import java.util.Map;

public interface SavedRecipeDataAccessInterface {

    Map<Recipe, Integer> getRecipesSaved();
}
