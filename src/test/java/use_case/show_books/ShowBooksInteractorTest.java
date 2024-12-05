package use_case.show_books;

import data_access.InMemoryBookClubDataAccessObject;
import entity.Book;
import entity.BookClub;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowBooksInteractorTest {

    @Test
    public void successTest(){
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub cookingClub = new BookClub("Cooking", "Culinary");
        cookingClub.addBook(new Book("book1", "author1", "book1des", 8.0));
        bookClubMap.put("Cooking", cookingClub);
        ShowBooksInputData inputData = new ShowBooksInputData("Cooking");
        ShowBooksDataAccessInterface showBooksDataAccessInterface = new InMemoryBookClubDataAccessObject(bookClubMap);

        ShowBooksOutputBoundary successPresenter = new ShowBooksOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowBooksOutputData outputData) {
                assertTrue(outputData.getTitles().contains("book1"));
            }
        };
        ShowBooksInteractor interactor = new ShowBooksInteractor(successPresenter, showBooksDataAccessInterface);
        interactor.execute(inputData);
    }

}
