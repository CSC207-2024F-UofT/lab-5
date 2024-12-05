package use_case.searchResults;

import data_access.APIMovieAccess;
import data_access.DBSearchResultsDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.CommonMovie;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.search_results.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsInteractorTest {

    @Test
    void successTest(){
        SearchResultsDataAccessInterface searchResultsDataAccess = new DBSearchResultsDataAccessObject();

        final CommonUserFactory userFactory = new CommonUserFactory();
        DBUserDataAccessObject dbUserDataAccess = new DBUserDataAccessObject(userFactory);

        final String searchQuery = "Home Alone";
        final List<String> movieTitles = Arrays.asList("Home Alone 2: Lost in New York", "Home Alone", "Home Alone 3");
        final List<String> posterPaths = Arrays.asList("/uuitWHpJwxD1wruFl2nZHIb4UGN.jpg",
                "/onTSipZ8R3bliBdKfPtsDuHTdlL.jpg", "/6uOadrCfle0n2LOOxHbgWEdnrm2.jpg");
        final List<CommonMovie> results = APIMovieAccess.formatedSearchedMovies(searchQuery);

        SearchResultsInputData searchResultsInputData = new SearchResultsInputData(searchQuery);

        SearchResultsOutputBoundary searchResultsPresenter = new SearchResultsOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("prepareFailView is not the method that should be called");
            }

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToMovieView(User currentUser, CommonMovie searchResult, boolean watched) {
                fail("switchToMovieView is not the method that should be called");
            }

            @Override
            public void prepareSuccessView(SearchResultsOutputData searchResultsOutputData) {
                // Verify the getter methods
                assertEquals(searchQuery, searchResultsOutputData.getSearchText());
                assertEquals(movieTitles, searchResultsOutputData.getMovieTitles());
                assertEquals(posterPaths, searchResultsOutputData.getPosterPaths());
                System.out.println(searchResultsOutputData.getResults());
            }
        };
        SearchResultsInputBoundary interactor =  new SearchResultsInteractor(searchResultsDataAccess,
                searchResultsPresenter, dbUserDataAccess);
        interactor.execute(searchResultsInputData);
    }

    @Test
    void failToFindResultsTest(){
        SearchResultsDataAccessInterface searchResultsDataAccess = new DBSearchResultsDataAccessObject();

        final CommonUserFactory userFactory = new CommonUserFactory();
        DBUserDataAccessObject dbUserDataAccess = new DBUserDataAccessObject(userFactory);

        SearchResultsInputData searchResultsInputData = new SearchResultsInputData("AHSFJKHASJFKASHF");

        SearchResultsOutputBoundary searchResultsPresenter = new SearchResultsOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                // This method should be called in the test case.
            }

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToMovieView(User currentUser, CommonMovie searchResult, boolean watched) {
                fail("switchToMovieView is not the method that should be called");
            }

            @Override
            public void prepareSuccessView(SearchResultsOutputData searchResultsOutputData) {
                fail("prepareSuccessView is not the method that should be called");
            }
        };
        SearchResultsInputBoundary interactor =  new SearchResultsInteractor(searchResultsDataAccess,
                searchResultsPresenter, dbUserDataAccess);
        interactor.execute(searchResultsInputData);
    }

    @Test
    void switchToHomeViewTest() {
        SearchResultsDataAccessInterface searchResultsDataAccess = new DBSearchResultsDataAccessObject();

        final CommonUserFactory userFactory = new CommonUserFactory();
        DBUserDataAccessObject dbUserDataAccess = new DBUserDataAccessObject(userFactory);

        SearchResultsOutputBoundary homeViewPresenter = new SearchResultsOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("prepareFailView is not the method that should be called");
            }

            @Override
            public void switchToHomeView() {
                // This method should be called in the test case.
            }

            @Override
            public void switchToMovieView(User currentUser, CommonMovie searchResult, boolean watched) {
                fail("switchToMovieView is not the method that should be called");
            }

            @Override
            public void prepareSuccessView(SearchResultsOutputData searchResultsOutputData) {
                fail("prepareSuccessView is not the method that should be called");
            }
        };

        SearchResultsInputBoundary interactor =  new SearchResultsInteractor(searchResultsDataAccess,
                homeViewPresenter, dbUserDataAccess);
        interactor.switchToHomeView();
    }

    @Test
    void switchToMovieViewTest() {
        SearchResultsDataAccessInterface searchResultsDataAccess = new DBSearchResultsDataAccessObject();

        final CommonUserFactory userFactory = new CommonUserFactory();
        final CommonMovie result = new CommonMovie("Home Alone");
        DBUserDataAccessObject dbUserDataAccess = new DBUserDataAccessObject(userFactory);

        SearchResultsOutputBoundary movieViewPresenter = new SearchResultsOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("prepareFailView is not the method that should be called");
            }

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToMovieView(User currentUser, CommonMovie searchResult, boolean watched) {
                // This method should be called in the test case.
            }

            @Override
            public void prepareSuccessView(SearchResultsOutputData searchResultsOutputData) {
                fail("prepareSuccessView is not the method that should be called");
            }
        };

        SearchResultsInputBoundary interactor =  new SearchResultsInteractor(searchResultsDataAccess,
                movieViewPresenter, dbUserDataAccess);
        interactor.switchToMovieView("Test123", result);
    }

}
