package interface_adapter.survey1;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class Survey1ViewModel extends ViewModel<Survey1State> {

    public static final String SUBMIT_LABEL = "Submit";

    public Survey1ViewModel() {
        super("survey step 1/2");
        setState(new Survey1State());
    }
}
