package use_case.leave_a_review;

import java.util.Date;

import entity.CommonMovieReviewFactory;
import entity.MovieReview;

/**
 * This must take the input data provided by the controller and format it into a movie review object
 * that is then passed to the DB access layer along with the User object to store the review associated
 * to said user in our DB.
 */
public class LeaveReviewInteractor implements LeaveReviewInputBoundary {
    private final LeaveReviewDataAccessInterface leaveReviewDataAccessInterface;
    private final LeaveReviewOutputBoundary leaveReviewPresenter;
    private final CommonMovieReviewFactory commonMovieReviewFactory;
    private Date currentDate = new Date();

    public LeaveReviewInteractor(LeaveReviewDataAccessInterface leaveReviewDataAccessInterface,
                                 LeaveReviewOutputBoundary leaveReviewPresenter) {
        this.leaveReviewDataAccessInterface = leaveReviewDataAccessInterface;
        this.leaveReviewPresenter = leaveReviewPresenter;
        this.commonMovieReviewFactory = new CommonMovieReviewFactory();
    }

    /**
     * This triggers the leaveReview method in our DB access layer that actually stores the review to the
     * user in our DB.
     * @param userID the userID, needed to fetch user information.
     * @param stars the star rating.
     * @param writtenReview the written component of this review.
     * @param movieTitle the title of this movie.
     */
    public void execute(String userID, Double stars, String writtenReview, String movieTitle) {

        final MovieReview movieReview = commonMovieReviewFactory.create(userID, currentDate, stars, writtenReview,
                movieTitle);
        leaveReviewDataAccessInterface.leaveReview(movieReview);

    }

    /**
     * Overloaded execute method.
     * @param userID the userID, needed to fetch user information.
     * @param stars the star rating.
     * @param movieTitle the title of this movie.
     */
    public void execute(String userID, Double stars, String movieTitle) {
        final MovieReview movieReview = commonMovieReviewFactory.create(userID, currentDate, stars,
                movieTitle);
        leaveReviewDataAccessInterface.leaveReview(movieReview);
    }

    @Override
    public void goBack() {
        leaveReviewPresenter.goBack();
    }

    /**
     * Go to the home screen.
     */
    public void goHome() {
        leaveReviewPresenter.goHome();
    }
}
