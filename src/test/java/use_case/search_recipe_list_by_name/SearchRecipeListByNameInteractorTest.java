package use_case.search_recipe_list_by_name;

import data_access.InMemorySearchRecipeListDataAccessObject;
import entity.Ingredient;
import entity.Recipe;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.search_recipe_list_by_ingredient.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SearchRecipeListByNameInteractorTest {
    private static final Recipe recipe1 = new Recipe(
            "recipe1 roasted in oven",
            "url",
            List.of(
                    new Ingredient("chicken", 1.0, "unit"),
                    new Ingredient("mushroom", 1.0, "unit")),
            "image");
    private static final Recipe recipe2 = new Recipe(
            "recipe2 roasted on grill",
            "url",
            List.of(
                    new Ingredient("tomato", 1.0, "unit"),
                    new Ingredient("onion", 1.0, "unit")),
            "image");
    private static final Recipe recipe3 = new Recipe(
            "recipe3 microwaved",
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
        final String recipeName = "roasted";

        SearchRecipeListByNameInputData inputData = new SearchRecipeListByNameInputData(
                recipeName, user, "bookmarks");
        SearchRecipeListByNameDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByNameOutputBoundary successPresenter =
                new SearchRecipeListByNameOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchRecipeListByNameOutputData searchResults) {
                assertEquals(List.of(recipe1, recipe2), searchResults.getRecipes());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchRecipeListByNameInputBoundary interactor =
                new SearchRecipeListByNameInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successTestForRecentlyViewed() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final String recipeName = "roasted";

        SearchRecipeListByNameInputData inputData = new SearchRecipeListByNameInputData(
                recipeName, user, "recentlyViewed");
        SearchRecipeListByNameDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByNameOutputBoundary successPresenter =
                new SearchRecipeListByNameOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByNameOutputData searchResults) {
                        assertEquals(List.of(recipe1, recipe2), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByNameInputBoundary interactor =
                new SearchRecipeListByNameInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successTestForFolder() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        folders.put("dinner", bookmarks);
        folders.put("lunch", List.of());
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final String recipeName = "roasted";

        SearchRecipeListByNameInputData inputData = new SearchRecipeListByNameInputData(
                recipeName, user, "dinner");
        SearchRecipeListByNameDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByNameOutputBoundary successPresenter =
                new SearchRecipeListByNameOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByNameOutputData searchResults) {
                        assertEquals(List.of(recipe1, recipe2), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByNameInputBoundary interactor =
                new SearchRecipeListByNameInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void emptyQueryTest() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);

        SearchRecipeListByNameInputData inputData = new SearchRecipeListByNameInputData(
                "", user, "bookmarks");
        SearchRecipeListByNameDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByNameOutputBoundary successPresenter =
                new SearchRecipeListByNameOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByNameOutputData searchResults) {
                        assertEquals(List.of(), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByNameInputBoundary interactor =
                new SearchRecipeListByNameInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void noMatchingNameTest() {
        final Map<String, List<Recipe>> folders = new HashMap<>();
        final User user = new User("cindy", "123", bookmarks, recentlyViewed, folders);
        final String recipeName = "steamed";

        SearchRecipeListByNameInputData inputData = new SearchRecipeListByNameInputData(
                recipeName, user, "bookmarks");
        SearchRecipeListByNameDataAccessInterface userRepository = new InMemorySearchRecipeListDataAccessObject();
        userRepository.addUser(user);

        SearchRecipeListByNameOutputBoundary successPresenter =
                new SearchRecipeListByNameOutputBoundary() {
                    @Override
                    public void prepareSuccessView(SearchRecipeListByNameOutputData searchResults) {
                        assertEquals(List.of(), searchResults.getRecipes());
                    }

                    @Override
                    public void prepareFailView(String error) {
                        fail("Use case failure is unexpected.");
                    }
                };

        SearchRecipeListByNameInputBoundary interactor =
                new SearchRecipeListByNameInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}