package use_case.shopping_list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.Recipe;

/**
 * The ShoppingList Interactor.
 */
public class ShoppingListInteractor implements ShoppingListInputBoundary {
    private final ShoppingListOutputBoundary shoppingListPresenter;
    private final ShoppingListDataAccessInterface recipeDataAccessObject;

    public ShoppingListInteractor(ShoppingListOutputBoundary shoppingListPresenter,
                                  ShoppingListDataAccessInterface shoppingListDataAccessObject) {
        this.shoppingListPresenter = shoppingListPresenter;
        this.recipeDataAccessObject = shoppingListDataAccessObject;
    }

    @Override
    public void execute(ShoppingListInputData shoppingListInputData) {
        final String username = shoppingListInputData.getUsername();
        final String[] recipeNames = shoppingListInputData.getRecipeNames();
        System.out.println("Recipe names: " + Arrays.toString(recipeNames));
        final Map<String, String> ingredientsMap = new HashMap<>();
        for (String recipeName : recipeNames) {
            if (recipeName == null || "null".equals(recipeName)) {
                continue;
                // Skip null or "null" recipe names
            }
            else {
                final Recipe recipe = recipeDataAccessObject.getOneRecipe(recipeName);
                final String stringIngredients = recipe.getIngredients();
                // Get ingredients as a formatted string

                // Parse the string into a Map
                final String[] lines = stringIngredients.split("\n");
                // Split by newline to get each ingredient
                for (String line : lines) {
                    final String[] parts = line.split(": ");
                    // Split each line by ": " to separate ingredient and measurement
                    if (parts.length == 2) {
                        // Ensure it has both ingredient and measurement
                        final String ingredient = parts[0].trim();
                        // Trim to clean up any whitespace
                        final String measurement = parts[1].trim();

                        // Check if the ingredient already exists in the map
                        if (ingredientsMap.containsKey(ingredient)) {
                            // Combine existing measurement with the new one
                            final String existingMeasurement = ingredientsMap.get(ingredient);
                            final String combinedMeasurement = combineMeasurements(existingMeasurement, measurement);
                            ingredientsMap.put(ingredient, combinedMeasurement);
                        }
                        else {
                            ingredientsMap.put(ingredient, measurement);
                        }
                    }
                }
            }
        }
        final ShoppingListOutputData shoppingListOutputData = new ShoppingListOutputData(username, recipeNames,
                ingredientsMap);
        shoppingListPresenter.presentShoppingList(shoppingListOutputData);
    }

    // Helper method to combine measurements (you may need to implement this logic)
    private String combineMeasurements(String measurement1, String measurement2) {
        // Implement logic to combine two measurement strings (e.g., "1 cup" + "2 cups" => "3 cups")
        // For now, just return them concatenated
        return measurement1 + " + " + measurement2;
    }
}