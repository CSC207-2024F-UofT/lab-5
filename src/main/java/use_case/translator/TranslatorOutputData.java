package use_case.translator;

/**
 * Output Data for the Translator Use Case.
 */
public class TranslatorOutputData {

    private final String outputText;
    private final boolean useCaseFailed;

    public TranslatorOutputData(String outputText, boolean useCaseFailed) {
        this.outputText = outputText;
        this.useCaseFailed = useCaseFailed;
    }

    public String getOutputText() {
        return outputText;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
