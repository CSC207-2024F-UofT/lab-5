package interface_adapter.forgotPassword;

import interface_adapter.ViewModel;

/**
 * View model for unimplemented functionality.
 */
public class ForgotPasswordViewModel extends ViewModel<ForgotPasswordState> {

    public ForgotPasswordViewModel() {
        super("Forgot Password");
        setState(new ForgotPasswordState());
    }
}
