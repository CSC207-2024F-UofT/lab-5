package interface_adapter.home;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

/**
 * The ViewModel for the Home View.
 */
public class HomeViewModel extends ViewModel<SignupState> {

    public static final String TITLE_LABEL = "Home View";

    public static final String TO_NEW_TASK_BUTTON_LABEL = "New Task";
    public static final String TO_CURR_TASKS_LABEL = "Current Tasks";

    public HomeViewModel() {
        super("home");
    }
}
