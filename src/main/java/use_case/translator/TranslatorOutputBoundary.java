package use_case.translator;

/**
 * The output boundary for the Translator Use Case.
 */
public interface TranslatorOutputBoundary {

    /**
     * Prepares the success view for the Translator Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(TranslatorOutputData outputData);

    /**
     * Prepares the failure view for the Translator Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
