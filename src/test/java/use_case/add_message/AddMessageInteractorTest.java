package use_case.add_message;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.Notes;
import entity.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class AddMessageInteractorTest {
    private static InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject;
    @BeforeEach
    public void setup() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        // adds 2 empty Notes in the book club
        bookClub.addNotes("favourite foods", new Notes("favourite foods", new ArrayList<>()));
        bookClub.addNotes("cooking tips", new Notes("cooking tips", new ArrayList<>()));
        bookClub.getNote("favourite foods").addMessage(new Message("user 1", "Pizza is my favourite"));
        bookClubMap.put("Cooking", bookClub);

        inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking");
        inMemoryBookClubDataAccessObject.setCurrentNote("favourite foods");
        // setting current book club and current Note which is done by other use cases.

    }

    @Test
    public void addMessageTest() {
        AddMessageInputData inputData = new AddMessageInputData("I also like pizza", "user 2");
        AddMessageOutputBoundary addMessagePresenter = new AddMessageOutputBoundary() {
            @Override
            public void prepareShowMessageView(AddMessageOutputData outputData) {
                List<AbstractMap.SimpleEntry<String, String>> expectedOutput = new ArrayList<>();
                expectedOutput.add(new AbstractMap.SimpleEntry<>("user 1", "Pizza is my favourite"));
                expectedOutput.add(new AbstractMap.SimpleEntry<>("user 2", "I also like pizza"));
                assertEquals(expectedOutput, outputData.getMessages());
            }
        };
        AddMessageInputBoundary addMessageInteractor = new AddMessageInteractor(inMemoryBookClubDataAccessObject, addMessagePresenter);
        addMessageInteractor.execute(inputData);

    }

    @Test
    public void showMessagesTest() {
        String discussion = "favourite foods";
        List<AbstractMap.SimpleEntry<String, String>> expectedOutput = new ArrayList<>();
        expectedOutput.add(new AbstractMap.SimpleEntry<>("user 1", "Pizza is my favourite"));
        AddMessageOutputBoundary showMessagesPresenter = new AddMessageOutputBoundary() {
            @Override
            public void prepareShowMessageView(AddMessageOutputData outputData) {
                Assertions.assertEquals(expectedOutput, outputData.getMessages());
                Assertions.assertEquals(discussion, outputData.getCurrentNote());
            }
        };
        AddMessageInputBoundary addMessageInteractor = new AddMessageInteractor(inMemoryBookClubDataAccessObject, showMessagesPresenter);
        addMessageInteractor.showMessages(discussion);


    }
}
