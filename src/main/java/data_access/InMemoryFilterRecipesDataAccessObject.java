package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;
import entity.Recipe;
import use_case.filter_recipes.FilterRecipesDataAccessInterface;

/**
 * In-memory implementation of the DAO for getting filtered recipes.
 */
public class InMemoryFilterRecipesDataAccessObject implements FilterRecipesDataAccessInterface {

//    private List<String> ingredients;
//    private String diet;
//    private String cuisine;

    @Override
    public List<Recipe> filterSearchRecipes(List<String> ingredients, String diet, String cuisine) {
        final List<Recipe> recipes = new ArrayList<>();
        final List<Ingredient> ingredientList = new ArrayList<>();
        for (String item : ingredients) {
            final Ingredient ingredient = new Ingredient(item, 1.0, "cup");
            ingredientList.add(ingredient);
        }
        if (("vegetarian".equals(diet)) && ("chinese".equals(cuisine))) {
            recipes.add(new Recipe("veggie fried rice", "url1", ingredientList, "image1"));
        }
        else if (("vegan".equals(diet)) && ("italian".equals(cuisine))) {
            recipes.add(new Recipe("vegan pasta", "url2", ingredientList, "image2"));
        }
        else {
            recipes.add(new Recipe("sandwich", "url3", ingredientList, "image3"));
        }
        return recipes;
    }

    @Override
    public List<String> getAvailableDiets() {
        final List<String> diets = new ArrayList<>();
        diets.add("vegetarian");
        diets.add("vegan");
        diets.add("ketogenic");
        return diets;
    }

    @Override
    public List<String> getAvailableCuisines() {
        final List<String> cuisines = new ArrayList<>();
        cuisines.add("italian");
        cuisines.add("chinese");
        cuisines.add("indian");
        return cuisines;
    }

}
