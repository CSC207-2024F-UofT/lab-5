package interface_adapter.join_club;

import interface_adapter.ViewModel;
/**
 * The View Model for the Join Club View.
 */

public class JoinClubViewModel extends ViewModel<JoinClubState> {

    public JoinClubViewModel() {
        super("JoinClub");
        setState(new JoinClubState());
    }
}
