package interface_adapter.join_club;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.join_club.JoinClubOutputBoundary;
import use_case.join_club.JoinClubOutputData;

/**
 * The Presenter for the Join Club Use Case.
 */

public class JoinClubPresenter implements JoinClubOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final LoggedInViewModel loggedInViewModel;

    private final JoinClubViewModel joinClubViewModel;

    public JoinClubPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel, JoinClubViewModel joinClubViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.joinClubViewModel = joinClubViewModel;
    }

    @Override
    public void prepareSuccessView(JoinClubOutputData response) {
        final String name = response.getUsername();
        final String clubName = response.getClubName();
        final JoinClubState joinClubState = joinClubViewModel.getState();
        joinClubState.setSucceesMessage("Congratulations, " + name + "! You have successfully joined the club \""
                + clubName + "\". Click Ok to see your book clubs in \"My Clubs\"");
        joinClubViewModel.setState(joinClubState);
        joinClubViewModel.firePropertyChanged("success");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final JoinClubState joinClubState = joinClubViewModel.getState();
        joinClubState.setErrorMessage(errorMessage);
        joinClubViewModel.setState(joinClubState);
        joinClubViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToLoggedInView() {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();
        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
