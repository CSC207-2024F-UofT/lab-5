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

    public void addRecipe(Recipe recipe, Integer amount) {
        recipes.put(recipe, amount);
    }

}
