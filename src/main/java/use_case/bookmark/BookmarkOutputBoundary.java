package use_case.bookmark;

/**
 * The output boundary for the Bookmark Use Case.
 * Defines the methods to present the results of bookmark actions.
 */
public interface BookmarkOutputBoundary {
    /**
     * Prepares the success view for a bookmark action.
     *
     * @param outputData The output data containing the result of the bookmark action.
     */
    void prepareSuccessView(BookmarkOutputData outputData);

    /**
     * Prepares the failure view for a bookmark action.
     *
     * @param errorMessage The error message describing why the bookmark action failed.
     */
    void prepareFailView(String errorMessage);
}