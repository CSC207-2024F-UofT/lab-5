package data_access;

import use_case.search_results.SearchResultsDataAccessInterface;

/**
 * The DAO for search results data.
 */
public class DBSearchResultsDataAccessObject implements SearchResultsDataAccessInterface {

    @Override
    public boolean existsByName(String title) {
        return title != null && !title.isEmpty();
    }
}
