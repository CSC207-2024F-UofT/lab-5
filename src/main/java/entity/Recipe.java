package entity;

import java.util.List;

/**
 * This class returns a recipe from the API.
 */
public class Recipe {
    private String id;
    private String name;
    private String url;
    private List<Ingredient> ingredients;
    private String cuisineType;

    public Recipe(String name, String url, List<Ingredient> ingredients) {
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    // Display the recipe as a string.
    @Override
    public String toString() {
        return this.name;
    }
}
