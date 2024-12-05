package use_case.create_club;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import org.junit.Test;
import use_case.join_club.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateClubInteractorTest {
    @Test
    public void successTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        String userName = "Jillian";
        String clubDes = "A club about ice-cream.";
        String clubName = "Ice-cream 4 Life";

        CreateClubInputData inputData = new CreateClubInputData(clubName, clubDes, userName);
        CreateClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        CreateClubOutputBoundary successPresenter = new CreateClubOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateClubOutputData outputData) {
                assertTrue(outputData.getUsername().equals(userName) &&
                        outputData.getClubname().equals(clubName) && outputData.getClubDes().equals(clubDes)) ;
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoggedInView() {
                fail("Use case switch is unexpected.");
            }
        };
        CreateClubInputBoundary createClubInteractor = new CreateClubInteractor(successPresenter, bookClubRepository);
        createClubInteractor.execute(inputData);
    }

    @Test
    public void failTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub testClub = new BookClub("Mystery Books", "Discussion about fun mystery books.");
        testClub.addMember("Bob");
        bookClubMap.put("Mystery Books", testClub); // creates a book club where Bob is a member
        CreateClubInputData inputData = new CreateClubInputData("Mystery Books", "Discussion about fun mystery books.", "Bob");

        CreateClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        CreateClubOutputBoundary failPresenter = new CreateClubOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateClubOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Mystery Books already exists.", error);

            }

            @Override
            public void switchToLoggedInView() {
                fail("Use case switch is unexpected.");
            }
        };
        CreateClubInputBoundary createClubInteractor = new CreateClubInteractor(failPresenter, bookClubRepository);
        createClubInteractor.execute(inputData);
    }

    @Test
    public void switchViewTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub testClub = new BookClub("Mystery Books", "Discussion about fun mystery books.");
        testClub.addMember("Bob");
        bookClubMap.put("Mystery Books", testClub); // creates a book club where Bob is a member
        CreateClubInputData inputData = new CreateClubInputData("Mystery Books", "Discussion about fun mystery books.", "Bob");

        CreateClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        CreateClubOutputBoundary failPresenter = new CreateClubOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateClubOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case switch is unexpected.");

            }

            @Override
            public void switchToLoggedInView() {
                //expected.
            }
        };
        CreateClubInputBoundary createClubInteractor = new CreateClubInteractor(failPresenter, bookClubRepository);
        createClubInteractor.switchToLoggedInView();
    }
}
