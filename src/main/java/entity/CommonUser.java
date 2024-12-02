package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final Map<Recipe, Integer> recipes;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.recipes = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Map<Recipe, Integer> getRecipes() {
        return recipes;
    }

    /**
     * Add Recipe to the user's saved recipes.
     * @param recipe the recipe object being saved.
     * @param amount rating of the recipe.
     */
    public void addRecipe(Recipe recipe, Integer amount) {
        recipes.put(recipe, amount);
    }

    public void reviewRecipe(Recipe recipe, int rating) {
        if (recipes.containsKey(recipe)) {
            recipes.put(recipe, rating);
        }
        else {
            throw new IllegalArgumentException("Recipe not found");
        }
    }
}

