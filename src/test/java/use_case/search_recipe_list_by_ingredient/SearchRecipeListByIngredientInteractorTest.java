package use_case.search_recipe_list_by_ingredient;

import data_access.InMemorySearchRecipeListDataAccessObject;
import entity.Ingredient;
import entity.Recipe;
import entity.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeListByIngredientInteractorTest {
    private static final Recipe recipe1 = new Recipe(
            "recipe1",
            "url",
            List.of(
                    new Ingredient("chicken", 1.0, "unit"),
                    new Ingredient("mushroom", 1.0, "unit")),
            "image");
    private static final Recipe recipe2 = new Recipe(
            "recipe2",
            "url",
            List.of(
                    new Ingredient("tomato", 1.0, "unit"),
                    new Ingredient("onion", 1.0, "unit")),
            "image");
    private static final Recipe recipe3 = new Recipe(
            "recipe3",
            "url",
            List.of(
                    new Ingredient("lettuce", 1.0, "unit"),
                    new Ingredient("pork", 1.0, "unit")),
            "image");
    private static final List<Recipe> bookmarks = List.of(recipe1, recipe2, recipe3);
    private static final List<Recipe> recentlyViewed = List.of(recipe1, recipe2, recipe3);

    @Test
    void successTestForBookmarks() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final List<String> ingredients = List.of("chicken", "mushroom", "tomato");

        SearchRecipeListByIngredientInputData inputData = new SearchRecipeListByIngredientInputData(
                ingredients, user, "bookmarks");
        SearchRecipeListByIngredientDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByIngredientOutputBoundary successPresenter =
                new SearchRecipeListByIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchRecipeListByIngredientOutputData searchResults) {
                assertEquals(List.of(recipe1, recipe2), searchResults.getRecipes());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchRecipeListByIngredientInputBoundary interactor =
                new SearchRecipeListByIngredientInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successTestForRecentlyViewed() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final List<String> ingredients = List.of("chicken", "mushroom", "tomato");

        SearchRecipeListByIngredientInputData inputData = new SearchRecipeListByIngredientInputData(
                ingredients, user, "recentlyViewed");
        SearchRecipeListByIngredientDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByIngredientOutputBoundary successPresenter =
                new SearchRecipeListByIngredientOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByIngredientOutputData searchResults) {
                        assertEquals(List.of(recipe1, recipe2), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByIngredientInputBoundary interactor =
                new SearchRecipeListByIngredientInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successTestForFolder() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        folders.put("dinner", bookmarks);
        folders.put("lunch", List.of());
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final List<String> ingredients = List.of("chicken", "mushroom", "tomato");

        SearchRecipeListByIngredientInputData inputData = new SearchRecipeListByIngredientInputData(
                ingredients, user, "dinner");
        SearchRecipeListByIngredientDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByIngredientOutputBoundary successPresenter =
                new SearchRecipeListByIngredientOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByIngredientOutputData searchResults) {
                        assertEquals(List.of(recipe1, recipe2), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByIngredientInputBoundary interactor =
                new SearchRecipeListByIngredientInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void emptyQueryTest() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);

        SearchRecipeListByIngredientInputData inputData = new SearchRecipeListByIngredientInputData(
                List.of(), user, "bookmarks");
        SearchRecipeListByIngredientDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByIngredientOutputBoundary successPresenter =
                new SearchRecipeListByIngredientOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByIngredientOutputData searchResults) {
                        assertEquals(List.of(), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByIngredientInputBoundary interactor =
                new SearchRecipeListByIngredientInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void noMatchingIngredientTest() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final List<String> ingredients = List.of("triangle", "tree");

        SearchRecipeListByIngredientInputData inputData = new SearchRecipeListByIngredientInputData(
                ingredients, user, "bookmarks");
        SearchRecipeListByIngredientDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByIngredientOutputBoundary successPresenter =
                new SearchRecipeListByIngredientOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByIngredientOutputData searchResults) {
                        assertEquals(List.of(), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByIngredientInputBoundary interactor =
                new SearchRecipeListByIngredientInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}