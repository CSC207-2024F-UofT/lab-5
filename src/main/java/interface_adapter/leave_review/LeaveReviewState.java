package interface_adapter.leave_review;

/**
 * State for LeaveReview Use Case.
 */
public class LeaveReviewState {
    private String username;
    private String movieName;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return this.movieName;
    }
}
