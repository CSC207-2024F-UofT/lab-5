package use_case.create_pitch;

/**
 * Input boundary for the Create Pitch use case.
 * Defines the contract for the interactor to execute the use case logic.
 */
public interface NewPitchInputBoundary {

    /**
     * Executes the Create Pitch use case with the given input data.
     * @param inputData the data needed to create the pitch.
     */
    void execute(NewPitchInputData inputData);
}