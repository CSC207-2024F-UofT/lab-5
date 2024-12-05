package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import use_case.email_validation.VerifaliaEmailValidator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SignupInteractorTest {

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("friendlygoose06@gmail.com", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("friendlygoose06@gmail.com", user.getUsername());
                assertTrue(userRepository.existsByName("friendlygoose06@gmail.com"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("friendlygoose06@gmail.com", "password", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("friendlygoose06@gmail.com", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add friendlygoose06@gmail.com to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("friendlygoose06@gmail.com", "pwd");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidEmailTest() throws IOException {
        SignupInputData inputData = new SignupInputData("invalidEmail", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Mock the VerifaliaEmailValidator to return false for invalid email
        mockStatic(VerifaliaEmailValidator.class);
        when(VerifaliaEmailValidator.validateEmail(anyString())).thenReturn(false);

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid email address. Please enter a valid email.", error);
            }

            @Override
            public void switchToLoginView() {
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);

        // Clean up static mock
        Mockito.framework().clearInlineMocks();
    }

    @Test
    void successValidEmailTest() throws IOException {
        SignupInputData inputData = new SignupInputData("huangterritory@gmail.com", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Mock the VerifaliaEmailValidator to return true for valid email
        mockStatic(VerifaliaEmailValidator.class);
        when(VerifaliaEmailValidator.validateEmail(anyString())).thenReturn(true);

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("huangterritory@gmail.com", user.getUsername());
                assertTrue(userRepository.existsByName("huangterritory@gmail.com"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);

        // Clean up static mock
        Mockito.framework().clearInlineMocks();
    }

    @Test
    void failureEmailValidationIOExceptionTest() throws IOException {
        SignupInputData inputData = new SignupInputData("test@example.com", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Mock the VerifaliaEmailValidator to throw an IOException
        mockStatic(VerifaliaEmailValidator.class);
        when(VerifaliaEmailValidator.validateEmail(anyString())).thenThrow(new IOException());

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("An error occurred during email validation. Please try again.", error);
            }

            @Override
            public void switchToLoginView() {
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);

        // Clean up static mock
        Mockito.framework().clearInlineMocks();
    }

    @Test
    void testSwitchToLoginView() {
        // Mock dependencies
        final SignupOutputBoundary mockOutputBoundary = mock(SignupOutputBoundary.class);
        final SignupUserDataAccessInterface mockDataAccess = mock(SignupUserDataAccessInterface.class);

        // Create the interactor with mocked dependencies
        final SignupInteractor interactor = new SignupInteractor(mockDataAccess, mockOutputBoundary, null);
        // Call the method
        interactor.switchToLoginView();

        // Verify that the output boundary's switchToLoginView method was called
        verify(mockOutputBoundary, times(1)).switchToLoginView();
    }
}