package interface_adapter.create_club;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.create_club.CreateClubOutputBoundary;
import use_case.create_club.CreateClubOutputData;

/**
 * Presenter for the CreateClub Usecase.
 */
public class CreateClubPresenter implements CreateClubOutputBoundary {

    private final CreateClubViewModel createClubViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public CreateClubPresenter(CreateClubViewModel createClubViewModel,
                               ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.createClubViewModel = createClubViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(CreateClubOutputData response) {
        final CreateClubState createClubState = createClubViewModel.getState();
        final String clubName = response.getClubname();
        createClubState.setSuccessMessage("Congratulations! You have successfully joined the club \""
                + clubName + "\". Click OK to see your book clubs in \"My Clubs\"");
        createClubViewModel.setState(createClubState);
        createClubViewModel.firePropertyChanged("success");

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final CreateClubState createClubState = createClubViewModel.getState();
        createClubState.setErrorMessage(errorMessage);
        createClubViewModel.setState(createClubState);
        createClubViewModel.firePropertyChanged("error");
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
