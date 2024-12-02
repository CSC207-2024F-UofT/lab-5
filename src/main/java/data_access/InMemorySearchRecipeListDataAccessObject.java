package data_access;

import entity.Ingredient;
import entity.Recipe;
import entity.User;
import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientDataAccessInterface;

import java.util.*;

public class InMemorySearchRecipeListDataAccessObject implements SearchRecipeListByIngredientDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public List<Recipe> searchRecipeListByIngredient(List<String> ingredients, User user, String folder) {
        final User userFromFile = users.get(user.getUsername());
        List<Recipe> recipeList = new ArrayList<>();
        final List<Recipe> results = new ArrayList<>();
        if ("bookmarks".equals(folder)) {
            recipeList.addAll(userFromFile.getBookmarks());
        }
        else if ("recentlyViewed".equals(folder)) {
            recipeList.addAll(userFromFile.getRecentlyViewed());
        }
        else {
            recipeList.addAll(userFromFile.getFolder(folder));
        }
        for (Recipe recipe : recipeList) {
            boolean match = false;
            final List<Ingredient> recipeIngredients = recipe.getIngredients();
            final List<String> recipeIngredientsString = new ArrayList<>();
            for (Ingredient ingredient : recipeIngredients) {
                final String[] words = ingredient.getName().split(" ");
                recipeIngredientsString.addAll(Arrays.asList(words));
            }
            for (String recipeIngredient : recipeIngredientsString) {
                for (String enteredIngredient : ingredients) {
                    if (recipeIngredient.equalsIgnoreCase(enteredIngredient)) {
                        match = true;
                    }
                }

            }
            if (match) {
                results.add(recipe);
            }
        }
        return results;
    }

    @Override
    public boolean addUser(User user) {
        users.put(user.getUsername(), user);
        return true;
    }
}
