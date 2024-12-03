package use_case.translator;

import entity.Translate;
import entity.TranslateFactory;

/**
 * The Translator Interactor.
 */

public class TranslatorInteractor implements TranslatorInputBoundary {
    private final TranslatorUserDataAccessInterface translatorDataAccessObject;
    private final TranslatorOutputBoundary translatorPresenter;
    private final TranslateFactory translateFactory;

    public TranslatorInteractor(TranslatorUserDataAccessInterface translatorDataAccessObject,
                                TranslatorOutputBoundary translatorPresenter,
                                TranslateFactory translateFactory) {
        this.translatorDataAccessObject = translatorDataAccessObject;
        this.translatorPresenter = translatorPresenter;
        this.translateFactory = translateFactory;
    }

    /**
     * Executes the translation process with the given input data.
     *
     * @param inputData the input data containing text and language details
     */

    public void execute(TranslatorInputData inputData) {
        final String inputText = inputData.getInputText();
        final String inputLanguage = inputData.getInputLanguage();
        final String outputLanguage = inputData.getOutputLanguage();

        if (inputText == null || inputText.isBlank()) {
            translatorPresenter.prepareFailView("Input text cannot be empty.");
        }

        else if (inputLanguage.equals(outputLanguage)) {
            translatorPresenter.prepareFailView("Input and output languages must be different.");
        }

        else {
            try {
                final Translate translateObject = translateFactory.create(inputLanguage, outputLanguage, inputText);
                final String translatedText = translateObject.getOutputText();
                translatorDataAccessObject.saveTranslation(translateObject);


                final TranslatorOutputData outputData = new TranslatorOutputData(translatedText, false);
                translatorPresenter.prepareSuccessView(outputData);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                translatorPresenter.prepareFailView("An error occurred during translation: "
                        + illegalArgumentException.getMessage());
            }
        }
    }
}
