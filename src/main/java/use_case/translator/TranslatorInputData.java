package use_case.translator;

/**
 * The input data for the Translator Use case.
 */
public class TranslatorInputData {
    private final String input;
    private final String inputLanguage;
    private final String outputLanguage;

//    public TranslatorInputData(String input) {
//        this.input = input;
//    }

    public TranslatorInputData(String input, String inputLanguage, String outputLanguage) {
        this.input = input;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
    }

    public String getInput() {
        return input;
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }
}
