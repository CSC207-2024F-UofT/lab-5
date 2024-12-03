package use_case.translator;

/**
 * Input Boundary for actions which are related to translate.
 */
public interface TranslatorInputBoundary {

    /**
     * Executes the Translator use case.
     * @param translatorInputData the input data.
     */
    void execute(TranslatorInputData translatorInputData);
}
