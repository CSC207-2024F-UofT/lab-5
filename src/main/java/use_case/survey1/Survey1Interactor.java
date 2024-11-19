package use_case.survey1;

import entity.CommonUser;
import use_case.login.LoginUserDataAccessInterface;

/**
 * The Survey1 Interactor.
 */
public class Survey1Interactor implements Survey1InputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final Survey1OutputBoundary survey1Presenter;

    public Survey1Interactor(LoginUserDataAccessInterface userDataAccessInterface,
                           Survey1OutputBoundary survey1OutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.survey1Presenter = survey1OutputBoundary;
    }

    public void execute(Survey1InputData survey1InputData, CommonUser user) {
        final String genre1 = survey1InputData.getGenre1();
        final String genre2 = survey1InputData.getGenre2();
        final String genre3 = survey1InputData.getGenre3();

        if (user.getPreferredGenres().isEmpty()) {
            survey1Presenter.prepareFailView("You must select at least one genre.");
        }
        else if (user.getPreferredGenres().size() == 1) {
            survey1Presenter.prepareFailView("You must select two more genres.");
        }
        else if (user.getPreferredGenres().size() == 2) {
            survey1Presenter.prepareFailView("You must select one more genre.");
        }
        else if (user.getPreferredGenres().size() == 3) {
            final Survey1OutputData survey1OutputData = new Survey1OutputData(genre1, genre2, genre3);
            survey1Presenter.prepareSuccessView(survey1OutputData);
        }
    }

    @Override
    public void execute(Survey1InputData survey1InputData) {
        final String genre1 = survey1InputData.getGenre1();
        final String genre2 = survey1InputData.getGenre2();
        final String genre3 = survey1InputData.getGenre3();

        if (genre1.isEmpty() || genre2.isEmpty() || genre3.isEmpty()) {
            survey1Presenter.prepareFailView("You must select at least one genre.");
        }
        else if (genre1.equals(genre2) || genre1.equals(genre3) || genre2.equals(genre3)) {
            survey1Presenter.prepareFailView("You must select three different genres.");
        }
        else {
            final Survey1OutputData survey1OutputData = new Survey1OutputData(genre1, genre2, genre3);
            survey1Presenter.prepareSuccessView(survey1OutputData);
        }
    }

    /**
     * Executes the switch to signup view use case.
     */
    @Override
    public void switchToSurveySecondPageView() {
        survey1Presenter.switchToSurveySecondPageView();
    }
}
