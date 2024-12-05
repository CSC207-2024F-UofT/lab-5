package use_case.bookclub_list;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import org.junit.jupiter.api.Test;
import use_case.exit_bookclub.ExitClubInteractor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BookClubInteractorTest {

    @Test
    public void successTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub cookingClub = new BookClub("Cooking", "Culinary");
        bookClubMap.put("Cooking", cookingClub);
        BookClubInputData inputData = new BookClubInputData();
        BookClubDataAccessInterface bookClubDataAccessInterface = new InMemoryBookClubDataAccessObject(bookClubMap);
        BookClubOutputBoundary successPresenter = new BookClubOutputBoundary() {
            @Override
            public void prepareSuccessView(BookClubOutputData outputData) {
                // Create the expected list of book club names
                List<String> expectedBookClubList = new ArrayList<>();
                expectedBookClubList.add("Cooking - Culinary");
                // Assert that the actual list matches the expected list
                assertEquals(expectedBookClubList, outputData.getBookClubListName(),
                        "The book club list should contain exactly 'Cooking - Culinary' and 'Reading - Literature'");
            }
        };
        BookClubInteractor interactor = new BookClubInteractor(successPresenter, bookClubDataAccessInterface);
        interactor.execute();
    }



    @Test
    public void failTest() {
        // Simulating a failure case: Empty map, no book clubs available
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClubInputData inputData = new BookClubInputData();
        BookClubDataAccessInterface bookClubDataAccessInterface = new InMemoryBookClubDataAccessObject(bookClubMap);

        // Fail presenter: assert that no clubs are returned
        BookClubOutputBoundary failPresenter = new BookClubOutputBoundary() {
            @Override
            public void prepareSuccessView(BookClubOutputData outputData) {
                assertTrue(outputData.getBookClubListName().isEmpty(), "Expected empty list, but found: " + outputData.getBookClubListName());
            }
        };
        BookClubInteractor interactor = new BookClubInteractor(failPresenter, bookClubDataAccessInterface);
        interactor.execute();
    }

    @Test
    public void invalidInputTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        bookClubMap.put("Cooking", new BookClub("Cooking", "Culinary"));

        // Null or invalid input data
        BookClubInputData inputData = null;  // Simulate invalid input
        BookClubDataAccessInterface bookClubDataAccessInterface = new InMemoryBookClubDataAccessObject(bookClubMap);

        BookClubOutputBoundary failPresenter = new BookClubOutputBoundary() {
            @Override
            public void prepareSuccessView(BookClubOutputData outputData) {
                // This should fail gracefully, maybe with an empty list or an error state
                assertNotNull(outputData.getBookClubListName(), "Book club list should not be null.");
            }
        };
        BookClubInteractor interactor = new BookClubInteractor(failPresenter, bookClubDataAccessInterface);
        interactor.execute();
    }

}
