package use_case.search_recipe_list_by_ingredient;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeListByIngredientInteractorTest {
    private static final List<String> ingredients = List.of("chicken", "mushroom", "tomato");

    @Test
    void successTest() {
        SearchRecipeListByIngredientInputData inputData = new SearchRecipeListByIngredientInputData(ingredients);
        SearchRecipeListByIngredientDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // For the success test, we need to add Paul to the data access repository before we log in.
        User user = factory.create("Paul", "password");
        userRepository.save(user);


        // This creates a successPresenter that tests whether the test case is as we expect.
        SearchRecipeListByIngredientOutputBoundary successPresenter =
                new SearchRecipeListByIngredientOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchRecipeListByIngredientOutputData outputData) {
                assertEquals("expected", ??user.getUsername()??);
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
    void noMatchingResultsTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrong");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // For this failure test, we need to add Paul to the data access repository before we log in, and
        // the passwords should not match.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void emptyQueryTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}