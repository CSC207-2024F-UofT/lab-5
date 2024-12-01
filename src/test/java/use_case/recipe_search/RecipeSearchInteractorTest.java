package use_case.recipe_search;

import data_access.RecipeDataAccessObject;
import entity.Recipe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RecipeSearchInteractorTest {

    @Test
    void successTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData("Pasta", "200", "400", "", "50","10","", "","20");
        RecipeSearchDataAccessInterface recipeRepository = new RecipeDataAccessObject();
        List<Recipe> results = recipeRepository.searchRecipe("Pasta", "200", "400", "", "50","10","", "","20");

        // Ensure the result is not null
        Assertions.assertNotNull(results);
        // Ensure the list is not empty
        Assertions.assertFalse(results.isEmpty());

        RecipeSearchOutputBoundary successPresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                // Ensure the list is not empty and contains the expected data
                Assertions.assertNotNull(results);
                Assertions.assertNotNull(results.getSearchResults());
                Assertions.assertFalse(results.getSearchResults().isEmpty());

                // Additional checks for specific data (if needed)
                for (Recipe recipe : results.getSearchResults()) {
                    Assertions.assertNotNull(recipe.getName());
                    Assertions.assertTrue(recipe.getCalories() >= 200 && recipe.getCalories() <= 500);
                }

                System.out.println("Success view prepared with results: " + results.getSearchResults().size());
            }

            @Override
            public void prepareFailView(String error) {
                Assertions.fail("Unexpected failure: " + error);
            }

            @Override
            public void switchToResultsView() {

            }

            @Override
            public void switchToProfileView() {

            }
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor((RecipeDataAccessObject) recipeRepository, successPresenter);
        interactor.execute(inputData);

    }

    @Test
    void failureNoFilterParametersTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData("", "", "", "", "", "", "", "", "");
        RecipeSearchOutputBoundary failurePresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                fail("Success view should not be prepared when no filter parameters are provided.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("At least one filter parameter must be provided.", error);
            }

            @Override
            public void switchToResultsView() {}

            @Override
            public void switchToProfileView() {}
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(new RecipeDataAccessObject(), failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidCalorieRangeTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData("", "500", "200", "", "", "", "", "", "");
        RecipeSearchOutputBoundary failurePresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                fail("Success view should not be prepared when the calorie range is invalid.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid calorie range: Minimum cannot be greater than maximum.", error);
            }

            @Override
            public void switchToResultsView() {}

            @Override
            public void switchToProfileView() {}
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(new RecipeDataAccessObject(), failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidCarbohydrateRangeTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData("", "", "", "100", "50", "", "", "", "");
        RecipeSearchOutputBoundary failurePresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                fail("Success view should not be prepared when the carbohydrate range is invalid.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid carbohydrate range: Minimum cannot be greater than maximum.", error);
            }

            @Override
            public void switchToResultsView() {}

            @Override
            public void switchToProfileView() {}
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(new RecipeDataAccessObject(), failurePresenter);
        interactor.execute(inputData);

    }

    @Test
    void failureInvalidProteinRangeTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData("", "", "", "", "", "300", "200", "", "");
        RecipeSearchOutputBoundary failurePresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                fail("Success view should not be prepared when the protein range is invalid.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid protein range: Minimum cannot be greater than maximum.", error);
            }

            @Override
            public void switchToResultsView() {}

            @Override
            public void switchToProfileView() {}
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(new RecipeDataAccessObject(), failurePresenter);
        interactor.execute(inputData);

    }

    @Test
    void failureInvalidFatRangeTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData("", "", "", "", "", "", "", "100", "50");
        RecipeSearchOutputBoundary failurePresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                fail("Success view should not be prepared when the fat range is invalid.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid fat range: Minimum cannot be greater than maximum.", error);
            }

            @Override
            public void switchToResultsView() {}

            @Override
            public void switchToProfileView() {}
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(new RecipeDataAccessObject(), failurePresenter);
        interactor.execute(inputData);

    }

    @Test
    void failureNoRecipesFoundTest() {
        // Given
        RecipeSearchInputData inputData = new RecipeSearchInputData(
                "Pasta", "200", "500", "50", "100", "200", "300", "50", "100");
        RecipeSearchOutputBoundary failurePresenter = new RecipeSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(RecipeSearchOutputData results) {
                fail("Success view should not be prepared when no recipes are found.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No recipes found for the given filters.", error);
            }

            @Override
            public void switchToResultsView() {}

            @Override
            public void switchToProfileView() {}
        };

        RecipeSearchInteractor interactor = new RecipeSearchInteractor(new RecipeDataAccessObject(), failurePresenter);
        interactor.execute(inputData);

    }


}
