package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addWatchlistsView()
                                            .addSurvey1View()
                                            .addMyReviewsView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            // .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addWatchlistsUseCase()
                                            .addHomeUseCase()
                                            .addMy_ReviewsUseCase()
                                            .build();
        
        application.pack();
        application.setVisible(true);
    }
}
