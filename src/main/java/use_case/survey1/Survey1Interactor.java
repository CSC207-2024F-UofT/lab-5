package use_case.survey1;

import java.util.List;

import entity.User;
import entity.UserFactory;

/**
 * The Survey1 Interactor.
 */
public class Survey1Interactor implements Survey1InputBoundary {
    private final Survey1UserDataAccessInterface userDataAccessObject;
    private final Survey1OutputBoundary survey1Presenter;
    private final UserFactory userFactory;

    public Survey1Interactor(Survey1UserDataAccessInterface survey1userDataAccessInterface,
                             Survey1OutputBoundary survey1OutputBoundary,
                             UserFactory userFactory) {
        this.userDataAccessObject = survey1userDataAccessInterface;
        this.survey1Presenter = survey1OutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(Survey1InputData survey1InputData, String username) {
        final List<String> selectedGenres = survey1InputData.getSelectedGenres();

        final Survey1OutputData survey1OutputData = new Survey1OutputData(selectedGenres);
        final User user = this.userDataAccessObject.get(username);
        user.addPreferredGenres(selectedGenres);
        final boolean success = this.userDataAccessObject.savePreferredGenres(user, user.getPreferredGenres());

        System.out.println("Survey1Interactor:execute:success: " + success);
        if (success) {
            survey1Presenter.prepareSuccessView(survey1OutputData);
        }
        else {
            survey1Presenter.prepareFailView("Failed to save preferred genres.");
        }
    }
}