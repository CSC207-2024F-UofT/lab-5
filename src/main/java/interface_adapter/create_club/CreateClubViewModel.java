package interface_adapter.create_club;

import interface_adapter.ViewModel;

/**
 * The View Model for the Create Club View.
 */

public class CreateClubViewModel extends ViewModel<CreateClubState> {

    public CreateClubViewModel() {
        super("create club");
        setState(new CreateClubState());
    }
}
