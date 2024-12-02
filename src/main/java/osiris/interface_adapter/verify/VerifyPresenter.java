package osiris.interface_adapter.verify;

import osiris.interface_adapter.ViewManagerModel;
import osiris.interface_adapter.signup.SignupViewModel;
import osiris.use_case.verify.VerifyOutputBoundary;
import osiris.use_case.verify.VerifyOutputData;

/**
 * The Presenter for the Verify CAPTCHA Use Case.
 */
public class VerifyPresenter implements VerifyOutputBoundary {
    private final VerifyViewModel verifyViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public VerifyPresenter(VerifyViewModel verifyViewModel, SignupViewModel signupViewModel,
                           ViewManagerModel viewManagerModel) {
        this.verifyViewModel = verifyViewModel;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(VerifyOutputData outputData) {
        VerifyState state = verifyViewModel.getState();
        state.setCaptchaCode(outputData.getCaptcha());
        verifyViewModel.setState(state);
        verifyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // Set the error message in the state
        VerifyState state = verifyViewModel.getState();
        state.setCaptchaError(error);
        verifyViewModel.setState(state);

        // Notify the view about the failure
        verifyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSignUpView() {
        // Switch back to the signup view if needed
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
