package interface_adapter.history;

import interface_adapter.ViewModel;

/**
 * The ViewModel for History View.
 */
public class HistoryViewModel extends ViewModel<HistoryState> {
    public static final String BACK_BUTTON_LABLE = "Back";

    public HistoryViewModel() {
        super("history");
        setState(new HistoryState());
    }
}
