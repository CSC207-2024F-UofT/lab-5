package use_case.home;

/**
 * The input data for the Home Use Case.
 */
public class HomeInputData {

    private final String password;
    private final String username;
    private final String query;

    public HomeInputData(String password, String username) {
        this.password = password;
        this.username = username;
        this.query = "";
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getQuery() {
        return query;
    }

}
