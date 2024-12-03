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
                                                .addProfileView()
                                                .addForgotPasswordView()
                                                .addSavedRecipesView()
                                                .addRecipeSearchView()
                                                .addSearchResultsView()
                                                .addSignupUseCase()
                                                .addLoginUseCase()
                                                .addChangePasswordUseCase()
                                                .addProfileUseCase()
                                                .addLogoutUseCase()
                                                .addSearchUseCase()
                                                .addSavedrecipesUseCase()
                                                .addSearchResultsUseCase()
                                                .build();

        application.pack();
        application.setVisible(true);
    }
}
