package entity;

import java.util.List;

/**
 * This class returns a recipe from the API.
 */
public class Recipe {
    private int id;
    private String name;
    private String url;
    private List<Ingredient> ingredients;
    private String cuisineType;
    // should correspond to "cuisines" in API recipes
    private String dietaryType;

    // Other attributes based on API documentation
    private String image;

    // TODO update constructor to initialize all the attributes
    public Recipe(String name, String url, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
    }

    // overloading the constructor
    public Recipe(String name, String url, List<Ingredient> ingredients, String cuisineType, String dietaryType) {
        this.name = name;
        this.url = url;
        this.ingredients = ingredients;
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

    // temporary method
    public void setImage(String image) {
        this.image = image;
    }

    // Display the recipe as a string.
    @Override
    public String toString() {
        return this.name;
    }

}
