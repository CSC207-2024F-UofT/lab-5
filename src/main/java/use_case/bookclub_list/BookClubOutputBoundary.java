package use_case.bookclub_list;

/**
 * Interface for the output boundary related to book club operations.
 * Defines methods for preparing the success view of book club-related data.
 */
public interface BookClubOutputBoundary {

    /**
     * Prepares the success view with the provided book club output data.
     *
     * @param outputData the output data to be displayed in the success view
     */
    void prepareSuccessView(BookClubOutputData outputData);
}
