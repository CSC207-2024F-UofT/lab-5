package filter_recipes_test;

import data_access.InMemoryFilterRecipesDataAccessObject;
import entity.Recipe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.filter_recipes.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FilterRecipesInteractorTest {

    @BeforeEach
    void setUp() {
        InMemoryFilterRecipesDataAccessObject mockDAO = new InMemoryFilterRecipesDataAccessObject();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAvailableDiets() {
        FilterRecipesOutputBoundary successPresenter = new FilterRecipesOutputBoundary() {
            @Override
            public void prepareSuccessView(FilterRecipesOutputData data) {
                // assert test
            }
            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };
        // List<String> diets =
    }

    @Test
    void getAvailableCuisines() {
    }

    @Test
    void filterSearchRecipes() {
        FilterRecipesDataAccessInterface testRepo = new InMemoryFilterRecipesDataAccessObject();

        FilterRecipesOutputBoundary successPresenter = new FilterRecipesOutputBoundary() {
            @Override
            public void prepareSuccessView(FilterRecipesOutputData data) {
                // assert test
                assertNotNull(data.getRecipes());
                assertEquals(data.getRecipes().size(), 1);
                assertEquals("vegan pasta", data.getRecipes().get(0).getName());
            }
            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        FilterRecipesInputData inputData = new FilterRecipesInputData(
                List.of("tomato", "cheese"), "vegan", "italian");
        FilterRecipesInputBoundary interactor = new FilterRecipesInteractor(testRepo, successPresenter);
        interactor.filterSearchRecipes(inputData);
    }
}