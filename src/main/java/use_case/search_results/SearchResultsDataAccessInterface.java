package use_case.search_results;

import entity.User;

public interface SearchResultsDataAccessInterface {

    String getCurrentUsername();

    User get(String username);
}
