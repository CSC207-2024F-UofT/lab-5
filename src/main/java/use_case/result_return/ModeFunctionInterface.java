package use_case.result_return;

/**
 * This is for the Quiz function for two modes.
 */
public interface ModeFunctionInterface {

    /**
     * What the mode should react if the answer is correct.
     */
    void correctReaction();

    /**
     * What the mode should react if the answer is incorrect.
     */
    void incorrectReaction();

}
