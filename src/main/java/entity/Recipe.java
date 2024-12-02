package entity;

import java.util.Map;
import java.util.Set;

/**
 * Recipe object.
 */
public class Recipe {

    private final String name;
    private final int servings;
    private final int calories;
    private final Map<String, Integer> nutrients;
    private final Set<String> tags;
    private final String url;
    private final String image;

    public Recipe(String name, int servings, int calories, Map<String, Integer> nutrients,
                  Set<String> tags, String url, String image) {
        this.name = name;
        this.servings = servings;
        this.calories = calories;
        this.nutrients = nutrients;
        this.tags = tags;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public int getCalories() {
        return calories;
    }

    public Map<String, Integer> getNutrients() {
        return nutrients;
    }

    public Set<String> getTags() {
        return tags;
    }

    /**
     * Method to build a recipe object.
     * @return Recipe builder class
     */
    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    /**
     * Recipe builder class.
     */
    public static class RecipeBuilder {
        private String name;
        private int servings;
        private int calories;
        private Map<String, Integer> nutrients;
        private Set<String> tags;
        private String url;
        private String image;

        RecipeBuilder() {
        }

        /**
         * Recipe name input.
         * @param nameInput name of recipe
         * @return sets the name of the recipe.
         */
        public RecipeBuilder name(String nameInput) {
            this.name = nameInput;
            return this;
        }

        /**
         * Recipe serving number input.
         * @param servingsInput serving number of recipe
         * @return sets the serving number of the recipe.
         */
        public RecipeBuilder servings(int servingsInput) {
            this.servings = servingsInput;
            return this;
        }

        /**
         * Recipe calories input.
         * @param caloriesInput calories of recipe
         * @return sets the calories of the recipe.
         */
        public RecipeBuilder calories(int caloriesInput) {
            this.calories = caloriesInput;
            return this;
        }

        /**
         * Recipe nutrients input.
         * @param nutrientsInput nutrients of recipe
         * @return sets the nutrients of the recipe.
         */
        public RecipeBuilder nutrients(Map<String, Integer> nutrientsInput) {
            this.nutrients = nutrientsInput;
            return this;
        }

        /**
         * Recipe tags input.
         * @param tagsInput tags of recipe
         * @return sets the tags of the recipe.
         */
        public RecipeBuilder tags(Set<String> tagsInput) {
            this.tags = tagsInput;
            return this;
        }

        /**
         * Recipe url input.
         * @param urlInput url of recipe
         * @return sets the url of the recipe.
         */
        public RecipeBuilder url(String urlInput) {
            this.url = urlInput;
            return this;
        }

        /**
         * Recipe image input.
         * @param imageInput image of recipe
         * @return sets the image of the recipe.
         */
        public RecipeBuilder image(String imageInput) {
            this.image = imageInput;
            return this;
        }

        /**
         * Builds the recipe.
         * @return New Recipe
         */
        public Recipe build() {
            return new Recipe(name, servings, calories, nutrients, tags, url, image);
        }
    }
}
