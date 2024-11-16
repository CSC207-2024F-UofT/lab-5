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
    private String cuisineType; // should correspond to "cuisines" in API recipes

    // Other attributes based on API documentation
    private String image;

    // TODO update constructor to initialize all the attributes
    public Recipe(String name, String url, List<Ingredient> ingredients) {
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
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

    // Display the recipe as a string.
    @Override
    public String toString() {
        return this.name;
    }

}
