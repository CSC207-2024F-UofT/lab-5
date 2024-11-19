package entity;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;



/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;

    // TODO - updated the lists once the classes are created.

    private boolean loginStatus;
    private List<String> ratingsAndReviews = new ArrayList<>();
    private List<String> watchlists = new ArrayList<>();
    private List<String> preferredGenres;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.loginStatus = false;
        this.preferredGenres = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean getLoginStatus() {
        return loginStatus;
    }

    /**
     * Returns the user created watchlists of the user.
     *
     * @return list of watchlists of the user.
     */
    @Override
    public List<String> getWatchlists() {
        return this.watchlists;
    }

    public List<String> getPreferredGenres() {
        return this.preferredGenres;
    }

    /**
     *  the user selects preferred genre.
     *
     * @param genre the genre to be added
     */
    public void addPreferredGenres(String genre) {
        this.preferredGenres.add(genre);
    }
}
