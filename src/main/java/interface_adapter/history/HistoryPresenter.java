package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

/**
 * The presenter for the History Use Case.
 */
public class HistoryPresenter implements HistoryOutputBoundary {
    private final HistoryViewModel historyViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public HistoryPresenter(HistoryViewModel historyViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.historyViewModel = historyViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare success view.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(HistoryOutputData outputData) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setHistory(outputData.getHistory());
        this.historyViewModel.setState(historyState);
        this.historyViewModel.firePropertyChanged("history");

        this.viewManagerModel.setState(historyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare failed view.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setHistoryError(errorMessage);
        historyViewModel.firePropertyChanged("history");
    }

    @Override
    public void switchBackToTranslateView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHistoryView() {
        viewManagerModel.setState(historyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
