package use_case.history;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Translate;
import entity.User;

/**
 * The History Interactor.
 */
public class HistoryInteractor implements HistoryInputBoundary {

    private final HistoryUserDataAccessInterface historyDataAccessObject;
    private final HistoryOutputBoundary historyPresenter;

    public HistoryInteractor(HistoryUserDataAccessInterface historyDataAccessObject,
                             HistoryOutputBoundary historyPresenter) {
        this.historyDataAccessObject = historyDataAccessObject;
        this.historyPresenter = historyPresenter;
    }

    /**
     * @param historyInputData the input data
     */
    @Override
    public void execute(HistoryInputData historyInputData) {
        final User user = historyInputData.getUser();
        final List<Translate> allTranslation = historyDataAccessObject.getAllTranslationsByUser(user);
        if (allTranslation == null || allTranslation.isEmpty()) {
            historyPresenter.prepareFailView("There is no searching history currently.");
        }

        else {
            try {
                final Map<String, String> history = new HashMap<>();
                for (Translate translate : allTranslation) {
                    history.put(translate.getInputText(), translate.getOutputText());
                }
                final HistoryOutputData outputData = new HistoryOutputData(user, history, false);
                historyPresenter.prepareSuccessView(outputData);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                historyPresenter.prepareFailView("An error occurred. "
                        + illegalArgumentException.getMessage());
            }
        }
    }

    /**
     * Switch back to Translate View.
     */
    @Override
    public void switchBackToTranslateView() {
        historyPresenter.switchBackToTranslateView();
    }

    @Override
    public void switchToHistoryView() {
        historyPresenter.switchToHistoryView();
    }
}
