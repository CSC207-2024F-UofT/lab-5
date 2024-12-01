package use_case.translator;

import entity.Translate;

/**
 * The Translator Interactor.
 */

public class TranslatorInteractor implements TranslatorInputBoundary {
    private final Translate translatorService;
    private final TranslatorOutputBoundary translatorPresenter;

    public TranslatorInteractor(Translate translatorService,
                                TranslatorOutputBoundary translatorPresenter) {
        this.translatorService = translatorService;
        this.translatorPresenter = translatorPresenter;
    }

    /**
     * Executes the translation process with the given input data.
     *
     * @param inputData the input data containing text and language details
     */

    public void execute(TranslatorInputData inputData) {
        final String inputText = inputData.getInput();
        final String inputLanguage = inputData.getInputLanguage();
        final String outputLanguage = inputData.getOutputLanguage();

        //        if (inputText == null || inputText.isBlank()) {
        //            translatorPresenter.prepareFailView("Input text cannot be empty.");
        //            return;
        //
        //        if (inputLanguage.equals(outputLanguage)) {
        //            translatorPresenter.prepareFailView("Input and output languages must be different.");
        //            return;
        //        }

        try {

            final String translatedText = Translate.translateText(inputText, inputLanguage, outputLanguage);

            final TranslatorOutputData outputData = new TranslatorOutputData(translatedText);
            translatorPresenter.prepareSuccessView(outputData);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            translatorPresenter.prepareFailView("An error occurred during translation: "
                    + illegalArgumentException.getMessage());
        }
    }
}
