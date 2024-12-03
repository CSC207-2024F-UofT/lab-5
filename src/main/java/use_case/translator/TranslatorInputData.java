package use_case.translator;

/**
 * The input data for the Translator Use case.
 */
public class TranslatorInputData {
    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;

    public TranslatorInputData(String inputLanguage, String outputLanguage, String inputText) {
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }

    public String getInputText() {
        return inputText;
    }
}
