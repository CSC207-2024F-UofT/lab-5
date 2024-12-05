package use_case.join_club;

import data_access.InMemoryBookClubDataAccessObject;
import entity.Book;
import entity.BookClub;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JoinClubInteractorTest {
    @Test
    public void successTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        bookClubMap.put("Cooking", new BookClub("Cooking", "Culinary")); // creates empty book club

        JoinClubInputData inputData = new JoinClubInputData("Bob", "Cooking");
        JoinClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        JoinClubOutputBoundary successPresenter = new JoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(JoinClubOutputData outputData) {
                assertTrue(outputData.getUsername().equals("Bob") && outputData.getClubName().equals("Cooking"));
            }

            @Override
            public void prepareFailView(String message) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoggedInView() {

            }
        };
        JoinClubInputBoundary joinClubInteractor = new JoinClubInteractor(successPresenter, bookClubRepository);
        joinClubInteractor.execute(inputData);
    }

    @Test
    public void failTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub testClub = new BookClub("Cooking", "Culinary");
        testClub.addMember("Bob");
        bookClubMap.put("Cooking", testClub); // creates a book club where Bob is a member

        JoinClubInputData inputData = new JoinClubInputData("Bob", "Cooking");
        JoinClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        JoinClubOutputBoundary failPresenter = new JoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(JoinClubOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User Bob is already a member of the Cooking Book Club", error);

            }

            @Override
            public void switchToLoggedInView() {

            }
        };
        JoinClubInputBoundary joinClubInteractor = new JoinClubInteractor(failPresenter, bookClubRepository);
        joinClubInteractor.execute(inputData);
    }

}
