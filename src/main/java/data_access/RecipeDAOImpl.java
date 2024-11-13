package data_access;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {
    @Override
    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pasta", "https://example.com/pasta", List.of("pasta", "tomato sauce")));
        recipes.add(new Recipe("Salad", "https://example.com/salad", List.of("lettuce", "tomato", "cucumber")));
        recipes.add(new Recipe("Tomato Soup", "https://example.com/tomato_soup", List.of("tomato", "onion", "garlic")));
        return recipes;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        // Mock data - replace this with actual data retrieval from a database or API
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pasta", "https://example.com/pasta", List.of("pasta", "tomato sauce")));
        recipes.add(new Recipe("Salad", "https://example.com/salad", List.of("lettuce", "tomato", "cucumber")));
        recipes.add(new Recipe("Tomato Soup", "https://example.com/tomato_soup", List.of("tomato", "onion", "garlic")));
        return recipes;
    }
}
