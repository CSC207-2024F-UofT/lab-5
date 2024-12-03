package interface_adapter.home;

import interface_adapter.ViewModel;

/**
 * The View Model for the Home View.
 */
public class HomeViewModel extends ViewModel<LoggedInState> {

    public HomeViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
