package interface_adapter.my_clubs;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.show_notes.ShowNotesState;
import interface_adapter.show_notes.ShowNotesViewModel;
import use_case.my_clubs.MyClubsOutputBoundary;
import use_case.my_clubs.MyClubsOutputData;

/**
 * The presenter for my clubs usecase.
 */
public class MyClubsPresenter implements MyClubsOutputBoundary {
    private final MyClubsViewModel myClubsViewModel;
    private final ShowNotesViewModel showNotesViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public MyClubsPresenter(MyClubsViewModel myClubsViewModel, ShowNotesViewModel showNotesViewModel,
                            LoggedInViewModel loggedInViewModel,
                            ViewManagerModel viewManagerModel) {
        this.myClubsViewModel = myClubsViewModel;
        this.showNotesViewModel = showNotesViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MyClubsOutputData myClubsOutputData) {
        final MyClubsState myClubsState = myClubsViewModel.getState();
        myClubsState.setMyClubs(myClubsOutputData.getMyClubs());
        myClubsViewModel.setState(myClubsState);
        myClubsViewModel.firePropertyChanged();

        viewManagerModel.setState(myClubsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        loggedInViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToShowNotesView(String currentClub) {
        final ShowNotesState showNotesState = showNotesViewModel.getState();
        showNotesState.setCurrentClub(currentClub);
        showNotesViewModel.setState(showNotesState);
        showNotesViewModel.firePropertyChanged("current club");

        viewManagerModel.setState(showNotesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
