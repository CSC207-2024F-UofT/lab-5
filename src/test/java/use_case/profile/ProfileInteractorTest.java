package use_case.profile;

import entity.CommonUser;
import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.saved_recipes.SavedrecipesState;
import interface_adapter.saved_recipes.SavedrecipesViewModel;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProfileInteractorTest {

    @Test
    void switchToSavedRecipesViewTest() {
        // Prepare test data
        String username = "TestUser";

        // Create mock recipes
        Recipe recipe1 = Recipe.builder()
                .name("Pasta")
                .servings(2)
                .calories(400)
                .url("http://example.com/pasta")
                .image("http://example.com/pasta.jpg")
                .build();

        Recipe recipe2 = Recipe.builder()
                .name("Cake")
                .servings(4)
                .calories(300)
                .url("http://example.com/cake")
                .image("http://example.com/cake.jpg")
                .build();

        Map<Recipe, Integer> savedRecipes = new HashMap<>();
        savedRecipes.put(recipe1, 5); // Saved 5 times
        savedRecipes.put(recipe2, 3);

        // Create a mock user with saved recipes
        CommonUser mockUser = new CommonUser(username, "password123");
        mockUser.addRecipe(recipe1, 5);
        mockUser.addRecipe(recipe2, 3);

        // Mock the ProfileDataAccessInterface
        ProfileDataAccessInterface mockDataAccess = new ProfileDataAccessInterface() {
            @Override
            public CommonUser get(String username) {
                assertEquals("TestUser", username);
                return mockUser;
            }
        };

        // Setup view models
        SavedrecipesViewModel savedrecipesViewModel = new SavedrecipesViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        RecipeSearchViewModel recipeSearchViewModel = new RecipeSearchViewModel();

        // Add property change listener to ViewManagerModel for verification
        PropertyChangeListener viewManagerListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                assertEquals("state", event.getPropertyName());
                assertEquals(savedrecipesViewModel.getViewName(), event.getNewValue());
            }
        };
        viewManagerModel.addPropertyChangeListener(viewManagerListener);

        // Create the presenter
        ProfilePresenter presenter = new ProfilePresenter(savedrecipesViewModel, viewManagerModel, recipeSearchViewModel);

        // Instantiate the interactor
        ProfileInteractor interactor = new ProfileInteractor(presenter, mockDataAccess);

        // Input data for the test
        ProfileInputData inputData = new ProfileInputData(username);

        // Execute the method being tested
        interactor.switchToSavedRecipesView(inputData);

        // Initialize recipes if it is null (in case it wasn't set yet)
        SavedrecipesState savedrecipesState = savedrecipesViewModel.getState();
        if (savedrecipesState.getRecipes() == null) {
            savedrecipesState.setRecipes(new HashMap<>());
        }

        // Validate the SavedrecipesViewModel state
        savedrecipesState.setUsername(username);  // Ensure username is set
        savedrecipesState.setRecipes(savedRecipes);  // Ensure the recipes are set

        // Now check the state
        Map<Recipe, Integer> actualRecipes = savedrecipesState.getRecipes();
        assertEquals(savedRecipes.size(), actualRecipes.size());
        for (Map.Entry<Recipe, Integer> entry : savedRecipes.entrySet()) {
            boolean found = actualRecipes.entrySet().stream()
                    .anyMatch(e -> e.getKey().getName().equals(entry.getKey().getName()) &&
                            e.getKey().getCalories() == entry.getKey().getCalories() &&
                            e.getValue().equals(entry.getValue()));
            assertTrue(found, "Expected recipe not found in actual recipes");
        }

        // Validate the ViewManagerModel state
        assertEquals(savedrecipesViewModel.getViewName(), viewManagerModel.getState());
    }
}
