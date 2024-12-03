package use_case.search_results;

import data_access.APIMovieAccess;
import data_access.DBUserDataAccessObject;
import entity.CommonMovie;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The Search Results Interactor.
 */
public class SearchResultsInteractor implements SearchResultsInputBoundary {
    private final SearchResultsOutputBoundary searchResultsPresenter;
    private final SearchResultsDataAccessInterface searchResultsDataAccess;
    private final DBUserDataAccessObject dbUserDataAccess;

    public SearchResultsInteractor(SearchResultsDataAccessInterface searchResultsDataAccess,
                                   SearchResultsOutputBoundary searchResultsOutputBoundary, DBUserDataAccessObject dbUserDataAccess) {
        this.searchResultsDataAccess = searchResultsDataAccess;
        this.searchResultsPresenter = searchResultsOutputBoundary;
        this.dbUserDataAccess = dbUserDataAccess;
    }

    @Override
    public void execute(SearchResultsInputData searchResultsInputData) {
        final String searchText = searchResultsInputData.getSearchText();

        // if there are no movies, then raise an error
        if (!searchResultsDataAccess.existsByName(searchText)) {
            searchResultsPresenter.prepareFailView(searchText + ": Cannot be found.");
        }
        else {
            // get the info from the API
            final List<CommonMovie> results = APIMovieAccess.formatedSearchedMovies(searchText);

            // get the movies and posters
            final List<String> movieTitles = new ArrayList<>();
            final List<String> posterPaths = new ArrayList<>();

            for (int i = 0; i < results.size(); i++) {
                final CommonMovie movie = results.get(i);

                movieTitles.add(movie.getTitle());
                posterPaths.add(movie.getPoster());
            }

            // store the output data
            final SearchResultsOutputData searchResultsOutputData = new SearchResultsOutputData(searchText,
                    movieTitles, posterPaths, results);
            searchResultsPresenter.prepareSuccessView(searchResultsOutputData);
        }
    }

    /**
     * Executes the switch to Home view use case.
     */
    @Override
    public void switchToHomeView() {
        searchResultsPresenter.switchToHomeView();
    }

    /**
     * Executes the switch to movie view use case.
     */
    @Override
    public void switchToMovieView(String user, CommonMovie searchResult) {
        final User currentUser = dbUserDataAccess.get(user);
        final boolean watched = currentUser.watchedBefore(searchResult);

        searchResultsPresenter.switchToMovieView(currentUser, searchResult, watched);

    }
}
