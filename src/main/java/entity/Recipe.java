package entity;

import java.util.List;

/**
 * This class returns a recipe from the API.
 */
public class Recipe {
    private final String name;
    private final String url;
    private final List<Ingredient> ingredients;
    private final String image;
    private final String cuisineType;
    private final String dietaryType;

    public Recipe(String name, String url, List<Ingredient> ingredients, String image) {
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
        this.image = image;
        this.cuisineType = "";
        this.dietaryType = "";
    }

    // overloading the constructor
    public Recipe(String name, String url, List<Ingredient> ingredients, String image, String cuisineType,
                  String dietaryType) {
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
        this.image = image;
        this.cuisineType = cuisineType;
        this.dietaryType = dietaryType;
    }

    // getters
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

    public String getDietaryType() {
        return dietaryType;
    }

    public String getImage() {
        return image;
    }

    // Display the recipe as a string.
    @Override
    public String toString() {
        return this.name;
    }

}
