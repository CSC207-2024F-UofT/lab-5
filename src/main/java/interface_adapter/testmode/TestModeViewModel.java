package interface_adapter.testmode;

import interface_adapter.ViewModel;

/**
 * The View Model for the Test Mode View.
 */
public class TestModeViewModel extends ViewModel<TestModeState> {

    public TestModeViewModel() {
        super("test mode");
        setState(new TestModeState());
    }
}
