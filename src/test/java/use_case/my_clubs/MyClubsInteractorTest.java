package use_case.my_clubs;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MyClubsInteractorTest {
    @Test
    public void successTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub club1 = new BookClub("Cooking", "Culinary");
        BookClub club2 = new BookClub("Comic fans", "A comic book club");
        club1.addMember("Bob");
        bookClubMap.put("Cooking", club1);
        bookClubMap.put("comic fans", club2);
        // Bob is only a member of the Cooking club
        MyClubsInputData myClubsInputData = new MyClubsInputData("Bob");
        MyClubsDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        MyClubsOutputBoundary successPresenter = new MyClubsOutputBoundary() {
            @Override
            public void prepareSuccessView(MyClubsOutputData myClubsOutputData) {
                Map<String, String> expectedOutput = new HashMap<>();
                expectedOutput.put("Cooking", "Culinary");
                Assertions.assertEquals(expectedOutput, myClubsOutputData.getMyClubs());
            }

            @Override
            public void prepareFailView() {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToShowNotesView(String currentClub) {
                fail("use case switch is unexpected");
            }
        };
        MyClubsInputBoundary myClubsInteractor = new MyClubsInteractor(bookClubRepository, successPresenter);
        myClubsInteractor.execute(myClubsInputData);
    }

    @Test
    public void failTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub club1 = new BookClub("Cooking", "Culinary");
        club1.addMember("Alice");
        bookClubMap.put("Cooking", club1);
        // Bob is not a member of any club
        MyClubsInputData myClubsInputData = new MyClubsInputData("Bob");
        MyClubsDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        MyClubsOutputBoundary successPresenter = new MyClubsOutputBoundary() {
            @Override
            public void prepareSuccessView(MyClubsOutputData myClubsOutputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView() {
                // expected
            }

            @Override
            public void switchToShowNotesView(String currentClub) {
                fail("use case switch is unexpected");
            }
        };
        MyClubsInputBoundary myClubsInteractor = new MyClubsInteractor(bookClubRepository, successPresenter);
        myClubsInteractor.execute(myClubsInputData);
    }

    @Test
    public void switchToShowNotesViewTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub club1 = new BookClub("Cooking", "Culinary");
        BookClub club2 = new BookClub("Comic fans", "A comic book club");
        bookClubMap.put("Cooking", club1);
        bookClubMap.put("comic fans", club2);
        // in the program we get current club from view
        String currentClub = "Cooking";
        MyClubsDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);


        MyClubsOutputBoundary successPresenter = new MyClubsOutputBoundary() {
            @Override
            public void prepareSuccessView(MyClubsOutputData myClubsOutputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView() {
                fail("Use case failure is unexpected");
            }

            @Override
            public void switchToShowNotesView(String currentClub) {
                assertEquals(currentClub, bookClubRepository.getCurrentClub());
            }
        };

        MyClubsInputBoundary myClubsInteractor = new MyClubsInteractor(bookClubRepository, successPresenter);
        myClubsInteractor.switchToShowNotesView(currentClub);
    }
}