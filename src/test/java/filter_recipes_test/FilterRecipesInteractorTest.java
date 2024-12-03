package filter_recipes_test;

import data_access.InMemoryFilterRecipesDataAccessObject;
import org.junit.jupiter.api.Test;
import use_case.filter_recipes.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FilterRecipesInteractorTest {

    @Test
    void getAvailableDiets() {
        FilterRecipesDataAccessInterface testRepo = new InMemoryFilterRecipesDataAccessObject();
        FilterRecipesInputBoundary interactor = new FilterRecipesInteractor(testRepo);
        List<String> diets = interactor.getAvailableDiets();
        assertNotNull(diets);
        assertTrue(diets.contains("vegetarian"));
        assertTrue(diets.contains("vegan"));
    }

    @Test
    void getAvailableCuisines() {
        FilterRecipesDataAccessInterface testRepo = new InMemoryFilterRecipesDataAccessObject();
        FilterRecipesInputBoundary interactor = new FilterRecipesInteractor(testRepo);
        List<String> cuisines = interactor.getAvailableCuisines();
        assertNotNull(cuisines);
        assertTrue(cuisines.contains("italian"));
        assertTrue(cuisines.contains("chinese"));
    }

    @Test
    void filterSearchRecipes() {
        FilterRecipesDataAccessInterface testRepo = new InMemoryFilterRecipesDataAccessObject();

        FilterRecipesOutputBoundary successPresenter = new FilterRecipesOutputBoundary() {
            @Override
            public void prepareSuccessView(FilterRecipesOutputData data) {
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