package use_case.survey1;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Survey1InteractorTest {
    @Test
    public void toSurveySecondPageViewTest() {
        ArrayList<String> selectedGenres = new ArrayList<>(Arrays.asList("Action", "Adventure", "Comedy"));

        Survey1InputData survey1InputData = new Survey1InputData(selectedGenres);
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Alice", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Alice");

        Survey1OutputBoundary survey1presenter = new Survey1OutputBoundary() {

            @Override
            public void switchToSurveySecondPageView(User currentUser) {
                // This is expected
            }

            @Override
            public void prepareSuccessView(Survey1OutputData survey1OutputData) {
                assertEquals(selectedGenres, survey1OutputData.getSelectedGenres());
            }

            @Override
            public void prepareFailView(String error) { fail("Use case failure is unexpected.");}
        };
        Survey1InputBoundary survey1Interactor =
                new Survey1Interactor(userRepo, survey1presenter, new CommonUserFactory());
        survey1Interactor.execute(survey1InputData, "Alice");
    }
}
