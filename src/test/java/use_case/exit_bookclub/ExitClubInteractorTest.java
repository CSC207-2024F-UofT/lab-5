package use_case.exit_bookclub;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExitClubInteractorTest {

    @Test
    public void successTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub cookingClub = new BookClub("Cooking", "Culinary");
        cookingClub.addMember("Ali");
        bookClubMap.put("Cooking", cookingClub);

        ExitClubInputData inputData = new ExitClubInputData("Ali", "Cooking");
        ExitClubDataAccessInterface bookClubRep = new InMemoryBookClubDataAccessObject(bookClubMap);

        ExitClubOutputBoundary successPresenter = new ExitClubOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertFalse(bookClubMap.get("Cooking").getMembers().contains("Ali"));
            }
        };

        // Act: Execute the interactor
        ExitClubInteractor interactor = new ExitClubInteractor(successPresenter, bookClubRep);
        interactor.execute(inputData);
    }
}
