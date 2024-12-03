package interface_adapter.translate;

import use_case.translator.TranslatorInputBoundary;
import use_case.translator.TranslatorInputData;

/**
 * Controller for the Translate Use Case.
 */
public class TranslateController {
    private final TranslatorInputBoundary userTranslateUseCaseController;

    public TranslateController(TranslatorInputBoundary userTranslateUseCaseController) {
        this.userTranslateUseCaseController = userTranslateUseCaseController;
    }

    /**
     * Executes the Translate Use Case.
     * @param inputLanguage the input language
     * @param outputLanguage the output language
     * @param inputText the input text
     */
    public void execute(String inputLanguage, String outputLanguage, String inputText) {
        final TranslatorInputData translatorInputData = new TranslatorInputData(inputLanguage,
                outputLanguage, inputText);

        userTranslateUseCaseController.execute(translatorInputData);
    }

}
