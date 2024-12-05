package use_case.show_Notes;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.Notes;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ShowNotesInteractorTest {
    @Test
    public void SuccessTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        // adds 2 empty discussions in the book club
        bookClub.addNotes("favourite foods", new Notes("favourite foods", new ArrayList<>()));
        bookClub.addNotes("cooking tips", new Notes("cooking tips", new ArrayList<>()));
        bookClubMap.put("Cooking", bookClub);

        InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking"); // setting current book club which is done by another usecase.

        ShowNotesOutputBoundary successPresenter = new ShowNotesOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowNotesOutputData outputData) {
                List<String> expectedOutput = Arrays.asList("favourite foods", "cooking tips");
                assertEquals(expectedOutput, outputData.getTopics());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToAddMessageView(String discussion) {
                fail("Use case switch view is unexpected.");
            }
        };
        ShowNotesInputBoundary showNotesInteractor = new ShowNotesInteractor(inMemoryBookClubDataAccessObject, successPresenter);
        showNotesInteractor.execute();
    }


    @Test
    public void FailTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        bookClubMap.put("Cooking", bookClub); // adds book club with no discussions

        InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking"); // setting current book club which is done by another usecase.

        ShowNotesOutputBoundary failPresenter = new ShowNotesOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowNotesOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("There are no Notes here, create a new Note", errorMessage);
            }

            @Override
            public void switchToAddMessageView(String discussion) {
                fail("Use case switch view is unexpected.");
            }
        };

        ShowNotesInputBoundary showNotesInteractor = new ShowNotesInteractor(inMemoryBookClubDataAccessObject, failPresenter);
        showNotesInteractor.execute();
    }

    @Test
    public void SwitchViewTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        bookClub.addNotes("favourite foods", new Notes("favourite foods", new ArrayList<>()));
        bookClub.addNotes("cooking tips", new Notes("cooking tips", new ArrayList<>()));
        bookClubMap.put("Cooking", bookClub);

        InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking");
        String discussion = "favourite foods";

        ShowNotesOutputBoundary switchPresenter = new ShowNotesOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowNotesOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("use case failure is unexpected");
            }

            @Override
            public void switchToAddMessageView(String discussion) {
                assertEquals(inMemoryBookClubDataAccessObject.getCurrentNote(), discussion);
            }
        };
        ShowNotesInputBoundary showDiscussionsInteractor = new ShowNotesInteractor(inMemoryBookClubDataAccessObject, switchPresenter);
        showDiscussionsInteractor.switchToAddMessageView(discussion);
    }
}
