package entity;

import java.util.List;
import java.util.Objects;

/**
 * This class returns a recipe from the API.
 */
public class Recipe {
    private int id;
    private String name;
    private String url;
    private List<Ingredient> ingredients;
    private String image;
    private String cuisineType;
    // should correspond to "cuisines" in API recipes
    private String dietaryType;

    // TODO update constructor to initialize all the attributes
    public Recipe(String name, String url, List<Ingredient> ingredients, String image) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
        this.image = image;
    }

    // overloading the constructor
    public Recipe(String name, String url, List<Ingredient> ingredients, String image, String cuisineType, String dietaryType) {
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
        this.image = image;
        this.cuisineType = cuisineType;
        this.dietaryType = dietaryType;
    }

    // getters
    public int getId() {
        return id;
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
