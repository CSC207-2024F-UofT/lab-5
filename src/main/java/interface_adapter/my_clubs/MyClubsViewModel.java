package interface_adapter.my_clubs;

import interface_adapter.ViewModel;

/**
 * The state for my clubs ViewModel.
 */
public class MyClubsViewModel extends ViewModel<MyClubsState> {
    public static final String TITLE_LABEL = "My Book Clubs";
    public static final String NOTES_LABEL = "Notes";
    public static final String BOOKS_LABEL = "Books";
    public static final String EXIT_LABEL = "exit book club";

    public MyClubsViewModel() {
        super("my clubs");
        setState(new MyClubsState());
    }
}
