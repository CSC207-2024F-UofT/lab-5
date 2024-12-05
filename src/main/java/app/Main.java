
package app;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     * @throws URISyntaxException a URISyntax exception
     * @throws IOException a IOException
     */
    public static void main(String[] args) throws URISyntaxException, IOException {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSearchedView()
                .addSearchView()
                .addSignupView()
                .addMyClubsView()
                .addLoggedInView()
                .addJoinClubView()
                .addShowNotesView()
                .addAddMessageView()
                .addCreateClubView()
                .addShowBooksView()
                .addLogoutUseCase()
                .addSignupUseCase()
                .addChangePasswordUseCase()
                .addExitBookClubUseCase()
                .addBooksListUseCase()
                .addMyClubsUsecase()
                .addJoinClubUseCase()
                .addShowNotesUseCase()
                .addAddMessagesUseCase()
                .addLoginUseCase()
                .addCreateClubUseCase()
                .addBookClubListUseCase()
                .addSearchUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
