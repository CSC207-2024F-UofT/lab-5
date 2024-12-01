package use_case.translator;

/**
 * Output Data for the Translator Use Case.
 */
public class TranslatorOutputData {

    private final String outputText;

    public TranslatorOutputData(String outputText) {
        this.outputText = outputText;
    }

    public String getOutputText() {
        return outputText;
    }

}
