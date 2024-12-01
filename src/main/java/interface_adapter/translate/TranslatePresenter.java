package interface_adapter.translate;

import use_case.translator.TranslatorOutputBoundary;
import use_case.translator.TranslatorOutputData;

/**
 * The Presenter for the Translation Use Case.
 */
public class TranslatePresenter implements TranslatorOutputBoundary {

    private final TranslateViewModel translateViewModel;

    public TranslatePresenter(TranslateViewModel translateViewModel) {
        this.translateViewModel = translateViewModel;
    }

    @Override
    public void prepareSuccessView(TranslatorOutputData outputData) {
        translateViewModel.setTranslatedText(outputData.getTranslatedText());
        translateViewModel.firePropertyChanged("translatedText");
    }

    @Override
    public void prepareFailView(String error) {
        final TranslateState translateState = translateViewModel.getState();
        translateState.setTranslateError(error);
        translateViewModel.firePropertyChanged("translateError");
    }
}
