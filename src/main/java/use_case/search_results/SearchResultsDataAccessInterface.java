package use_case.search_results;

/**
 * DAO for the Search Results Use Case.
 */
public interface SearchResultsDataAccessInterface {

    /**
     * Checks if the given title exists.
     * @param title the title to look for
     * @return true if a movie with the given title exists; false otherwise
     */
    boolean existsByName(String title);
}
