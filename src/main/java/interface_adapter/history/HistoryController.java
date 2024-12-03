package interface_adapter.history;

import entity.User;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;

/**
 * The controller for the History Use Case.
 */
public class HistoryController {

    private final HistoryInputBoundary historyUseCaseInteractor;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
    }

    /**
     * Executes the History Use Case.
     * @param user the user logged in.
     */
    public void execute(User user) {
        final HistoryInputData historyInputData = new HistoryInputData(
                user);

        historyUseCaseInteractor.execute(historyInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchBackToTranslateView() {
        historyUseCaseInteractor.switchBackToTranslateView();
    }

    public void switchToHistoryView() {
        historyUseCaseInteractor.switchToHistoryView();
    }
}
