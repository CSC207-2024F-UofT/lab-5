package data_access;

import entity.Ingredient;
import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {
    private Ingredient pasta;
    private Ingredient tomatoSauce;
    private Ingredient lettuce;
    private Ingredient tomato;
    private Ingredient cucumber;
    private Ingredient onion;
    private Ingredient garlic;

    @Override
    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        this.pasta = new Ingredient("pasta");
        this.tomatoSauce = new Ingredient("tomato sauce");
        this.lettuce = new Ingredient("lettuce");
        this.tomato = new Ingredient("tomato");
        this.cucumber = new Ingredient("cucumber");
        this.onion = new Ingredient("onion");
        this.garlic = new Ingredient("garlic");
        recipes.add(new Recipe("Pasta", "https://example.com/pasta", List.of(pasta, tomatoSauce)));
        recipes.add(new Recipe("Salad", "https://example.com/salad", List.of(lettuce, tomato, cucumber)));
        recipes.add(new Recipe("Tomato Soup", "https://example.com/tomato_soup", List.of(tomato, onion, garlic)));
        return recipes;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        // Mock data - replace this with actual data retrieval from a database or API
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pasta", "https://example.com/pasta", List.of(pasta, tomato)));
        recipes.add(new Recipe("Salad", "https://example.com/salad", List.of(lettuce, tomato, cucumber)));
        recipes.add(new Recipe("Tomato Soup", "https://example.com/tomato_soup", List.of(tomato, onion, garlic)));
        return recipes;
    }
}
