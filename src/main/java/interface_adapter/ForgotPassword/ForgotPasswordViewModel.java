package interface_adapter.ForgotPassword;

import interface_adapter.ViewModel;

public class ForgotPasswordViewModel extends ViewModel<ForgotPasswordState> {

    public ForgotPasswordViewModel() {
        super("Forgot Password");
        setState(new ForgotPasswordState());
    }
}
