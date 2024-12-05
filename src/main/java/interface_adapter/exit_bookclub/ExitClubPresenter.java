package interface_adapter.exit_bookclub;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.exit_bookclub.ExitClubOutputBoundary;

/**
 * The Presenter for the Exit Club Use Case.
 */
public class ExitClubPresenter implements ExitClubOutputBoundary {

    private LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    public ExitClubPresenter(LoggedInViewModel loggedInViewModel,
                             ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
