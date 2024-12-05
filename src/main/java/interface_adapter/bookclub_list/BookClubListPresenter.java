package interface_adapter.bookclub_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.join_club.JoinClubState;
import interface_adapter.join_club.JoinClubViewModel;
import use_case.bookclub_list.BookClubOutputBoundary;
import use_case.bookclub_list.BookClubOutputData;

/**
 * Presenter for handling the book club list view logic.
 * Implements the BookClubOutputBoundary to prepare and display book club data.
 */
public final class BookClubListPresenter implements BookClubOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final JoinClubViewModel joinClubViewModel;
    private final LoggedInViewModel loggedInViewModel;

    /**
     * Constructor for initializing the BookClubListPresenter.
     * @param loggedInViewModel The current logged-in user's view model.
     * @param viewManagerModel The view manager responsible for updating views.
     * @param joinClubViewModel The view model for the join club functionality.
     */
    public BookClubListPresenter(
            final LoggedInViewModel loggedInViewModel,
            final ViewManagerModel viewManagerModel,
            final JoinClubViewModel joinClubViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.joinClubViewModel = joinClubViewModel;
    }

    /**
     * Prepares the success view by formatting the book club list.
     * Displays the list of book clubs in the view.
     * @param outputData The output data containing the book clubs.
     */
    @Override
    public void prepareSuccessView(final BookClubOutputData outputData) {
        // Get current states
        final JoinClubState joinClubState = joinClubViewModel.getState();

        joinClubState.setBookClubList(outputData.getBookClubListName());
        joinClubViewModel.setState(joinClubState);
        joinClubViewModel.firePropertyChanged();

        // Update the view manager state to reflect the current view
        viewManagerModel.setState(joinClubViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
