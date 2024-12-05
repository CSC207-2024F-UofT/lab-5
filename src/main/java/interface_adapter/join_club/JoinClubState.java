package interface_adapter.join_club;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of the JoinClub feature, holding information about the
 * current user, book clubs, whether the user has joined, and any error messages.
 */
public final class JoinClubState {

    private String username = "";

    private List<String> bookClubList = new ArrayList<>();

    private boolean joined;

    private String errorMessage = "";

    private String succeesMessage = "";

    /**
     * Gets the username of the user.
     *
     * @return the current username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username1 the username to be set
     */
    public void setUsername(final String username1) {
        this.username = username1;
    }

    /**
     * Gets a copy of the map of book clubs.
     *
     * @return a copy of the book club map
     */
    public List<String> getBookClubList() {
        return new ArrayList<>(bookClubList);
    }

    /**
     * Sets the list of the bookclub.
     *
     * @param bookClubList list of bookclubs.
     */
    public void setBookClubList(final List<String> bookClubList) {
        this.bookClubList.clear();
        this.bookClubList.addAll(bookClubList);
    }

    /**
     * Checks if the user has joined a book club.
     *
     * @return true if the user has joined a book club, false otherwise
     */
    public boolean isJoined() {
        return joined;
    }

    /**
     * Sets the joined status of the user.
     *
     * @param joined1 true if the user has joined a book club, false otherwise
     */
    public void setJoined(final boolean joined1) {
        this.joined = joined1;
    }

    /**
     * Gets the error message associated with the join club process.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage1 the error message to set
     */
    public void setErrorMessage(final String errorMessage1) {
        this.errorMessage = errorMessage1;
    }

    /**
     * Gets the sucess message associated with the join club process.
     *
     * @return the succees message
     */
    public String getSucceesMessage() {
        return succeesMessage;
    }

    /**
     * Sets the success message.
     *
     * @param succeesMessage  error message to set
     */
    public void setSucceesMessage(String succeesMessage) {
        this.succeesMessage = succeesMessage;
    }
}
