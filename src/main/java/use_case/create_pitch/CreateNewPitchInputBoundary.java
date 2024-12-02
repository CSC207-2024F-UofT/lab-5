package use_case.create_pitch;

/**
 * Input boundary for the Create Pitch use case.
 * Defines the contract for the interactor to execute the use case logic.
 */
public interface CreateNewPitchInputBoundary {

    /**
     * Executes the Create Pitch use case with the given input data.
     * @param createNewPitchInputData the data needed to create the pitch.
     */
    void execute(CreateNewPitchInputData createNewPitchInputData) throws Exception;
}
