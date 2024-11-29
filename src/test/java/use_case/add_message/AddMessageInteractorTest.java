package use_case.add_message;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.Discussion;
import entity.Message;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class AddMessageInteractorTest {
    @Test
    public void addMessageTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        // adds 2 empty discussions in the book club
        bookClub.addDiscussion("favourite foods", new Discussion("favourite foods", new ArrayList<>()));
        bookClub.addDiscussion("cooking tips", new Discussion("cooking tips", new ArrayList<>()));
        bookClub.getDiscussion("favourite foods").addMessage(new Message("user 1", "Pizza is my favourite"));
        bookClubMap.put("Cooking", bookClub);

        InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking");
        inMemoryBookClubDataAccessObject.setCurrentDiscussion("favourite foods");
        // setting current book club and current discussion which is done by other use cases.
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
}
