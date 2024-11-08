package entity;

import java.util.Map;
import java.util.Set;

public class Recipe {

    private final String name;
    private final int servings;
    private final int calories;
    private final Map<String, Integer> nutrients;
    private final Set<String> tags;

    public Recipe(String name, int servings, int calories, Map<String, Integer> nutrients, Set<String> tags) {
        this.name = name;
        this.servings = servings;
        this.calories = calories;
        this.nutrients = nutrients;
        this.tags = tags;
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

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder {
        private String name;
        private int servings;
        private int calories;
        private Map<String, Integer> nutrients;
        private Set<String> tags;

        RecipeBuilder() {
        }

        public RecipeBuilder name(String nameInput) {
            this.name = nameInput;
            return this;
        }

        public RecipeBuilder servings(int servingsInput) {
            this.servings = servingsInput;
            return this;
        }

        public RecipeBuilder calories(int caloriesInput) {
            this.calories = caloriesInput;
            return this;
        }

        public RecipeBuilder nutrients(Map<String, Integer> nutrientsInput) {
            this.nutrients = nutrientsInput;
            return this;
        }

        public RecipeBuilder tags(Set<String> tagsInput) {
            this.tags = tagsInput;
            return this;
        }

        public Recipe build() {
            return new Recipe(name, servings, calories, nutrients, tags);
        }
    }
}
