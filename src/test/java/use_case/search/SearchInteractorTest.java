package use_case.search;

import data_access.BookDataTransferObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    @Test
    void searchSuccessTest() {
        // Input Data
        SearchInputData inputData = new SearchInputData("The Lord of the Rings");

        // Mock Data Access
        SearchDataAccessInterface mockDataAccess = new SearchDataAccessInterface() {
            @Override
            public List<BookDataTransferObject> searchBookByTitle(String title) {
                List<BookDataTransferObject> results = new ArrayList<>();
                results.add(new BookDataTransferObject("The Lord of the Rings", "J.R.R. Tolkien", "OL12345", "https://covers.openlibrary.org/b/olid/OL12345-M.jpg"));
                return results;
            }
        };

        // Mock Output Boundary
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData data) {
                assertEquals("The Lord of the Rings", data.getTitle());
                assertEquals("J.R.R. Tolkien", data.getAuthor());
                assertEquals("OL12345", data.getKey());
                assertEquals("https://covers.openlibrary.org/b/olid/OL12345-M.jpg", data.getCoverUrl());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        // Interactor
        SearchInteractor interactor = new SearchInteractor(mockDataAccess, successPresenter);
        interactor.search(inputData);
    }

    @Test
    void searchEmptyQueryTest() {
        // Input Data
        SearchInputData inputData = new SearchInputData("");

        // Mock Data Access
        SearchDataAccessInterface mockDataAccess = title -> new ArrayList<>();

        // Mock Output Boundary
        SearchOutputBoundary failurePresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData data) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Search Query cannot be empty", errorMessage);
            }
        };

        // Interactor
        SearchInteractor interactor = new SearchInteractor(mockDataAccess, failurePresenter);
        interactor.search(inputData);
    }

    @Test
    void searchNoResultsTest() {
        // Input Data
        SearchInputData inputData = new SearchInputData("Unknown Book");

        // Mock Data Access
        SearchDataAccessInterface mockDataAccess = title -> new ArrayList<>();

        // Mock Output Boundary
        SearchOutputBoundary failurePresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData data) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Search failed. Enter a valid query", errorMessage);
            }
        };

        // Interactor
        SearchInteractor interactor = new SearchInteractor(mockDataAccess, failurePresenter);
        interactor.search(inputData);
    }

    @Test
    void searchExceptionTest() {
        // Input Data
        SearchInputData inputData = new SearchInputData("The Hobbit");

        // Mock Data Access
        SearchDataAccessInterface mockDataAccess = title -> {
            throw new RuntimeException("API failure");
        };

        // Mock Output Boundary
        SearchOutputBoundary failurePresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData data) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Search failed. Enter a valid query", errorMessage);
            }
        };

        // Interactor
        SearchInteractor interactor = new SearchInteractor(mockDataAccess, failurePresenter);
        interactor.search(inputData);
    }
}
