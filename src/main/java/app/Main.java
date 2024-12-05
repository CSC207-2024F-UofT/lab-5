package app;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     * @throws IOException if an error occurs while reading the file
     */
    public static void main(String[] args) throws IOException {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addWatchlistsView()
                                            .addWatchlistView()
                                            .addSurvey1View()
                                            .addMovieView()
                                            .addMyReviewsView()
                                            .addSearchResultsView()
                                            .addLeaveReviewView()
                                            .addSurveySecondPageView()
                                            .addSearchResultsView()

                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addWatchlistsUseCase()
                                            .addWatchlistUseCase()
                                            .addHomeUseCase()
                                            .addLeaveReviewUseCase()
                                            .addCreateWatchlistUseCase()
                                            .addRenameUseCase()
                                            .addDeleteUseCase()
                                            .addMyReviewsUseCase()
                                            .addSurvey1UseCase()
                                            .addSearchResultsUseCase()
                                            .addMovieUseCase()
                                            .addSurveySecondPageUseCase()
                                            .addSearchResultsUseCase()
                                            .addMovieUseCase()
                                            .addAddToWatchlistUseCase()
                                            .addRemoveUseCase()

                                            .build();
        
        application.pack();
        application.setVisible(true);
    }
}
