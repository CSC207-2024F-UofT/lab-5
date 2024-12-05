package interface_adapter.show_books;

import interface_adapter.ViewManagerModel;
import use_case.show_books.ShowBooksOutputBoundary;
import use_case.show_books.ShowBooksOutputData;

/**
 * The Presenter for the Join Club Use Case.
 */

public class ShowBooksPresenter implements ShowBooksOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final ShowBooksViewModel showBooksViewModel;

    public ShowBooksPresenter(ViewManagerModel viewManagerModel, ShowBooksViewModel showBooksViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.showBooksViewModel = showBooksViewModel;
    }

    /**
     * Excutes the presenter.
     * @param outputData The output data containing the books
     */
    @Override
    public void prepareSuccessView(ShowBooksOutputData outputData) {
        // Get current states
        final ShowBooksState showBooksState = showBooksViewModel.getState();
        showBooksState.setBookClubList(outputData.getTitles());
        showBooksViewModel.setState(showBooksState);
        showBooksViewModel.firePropertyChanged();
        viewManagerModel.setState(showBooksViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
