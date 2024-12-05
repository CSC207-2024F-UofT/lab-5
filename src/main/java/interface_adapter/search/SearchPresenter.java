package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

/**
 * Presenter for the Search use case.
 */
public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final SearchedViewModel searchedViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel searchViewModel,
                           ViewManagerModel viewManagerModel,
                           SearchedViewModel searchedViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchedViewModel = searchedViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData outputData) {
        // on success switch to searched view
        final SearchedState searchedState = searchedViewModel.getState();
        final String newTitle = outputData.getTitle();
        final String newAuthor = outputData.getAuthor();
        final String newKey = outputData.getKey();
        final String newCoverUrl = outputData.getCoverUrl();
        // transform output data into book view model
        final BookViewModel someBookViewModel = new BookViewModel(newTitle, newAuthor, newKey, newCoverUrl);
        searchedState.setBooks(someBookViewModel);
        searchedState.setQuery(outputData.getQuery());
        searchedState.setMessage("Search completed successfully");
        searchedViewModel.setState(searchedState);
        searchedViewModel.firePropertyChanged();

        // Update SearchState to indicate that the search has finished
        final SearchState searchState = searchViewModel.getState();
        searchState.setLoading(false);
        searchState.setErrorMessage("");
        searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();

        viewManagerModel.setState(searchedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SearchState searchState = searchViewModel.getState();
        final SearchedState searchedState = searchedViewModel.getState();
        searchedState.setBooks(new BookViewModel());
        searchedState.setMessage(errorMessage);
        searchedState.setQuery("");
        searchedViewModel.setState(searchedState);
        searchedViewModel.firePropertyChanged();

        searchState.setLoading(false);
        searchState.setErrorMessage(errorMessage);
        searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();
    }

    private BookViewModel dataTransformer(String title, String author, String key, String coverUrl) {
        final BookViewModel bookViewModel = new BookViewModel(title, author, key, coverUrl);
        return bookViewModel;
    }
}
