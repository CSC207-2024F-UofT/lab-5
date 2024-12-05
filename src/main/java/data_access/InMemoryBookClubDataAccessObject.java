package data_access;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Book;
import entity.BookClub;
import entity.Message;
import use_case.add_message.AddMessageDataAccessInterface;
import use_case.bookclub_list.BookClubDataAccessInterface;
import use_case.create_club.CreateClubDataAccessInterface;
import use_case.exit_bookclub.ExitClubDataAccessInterface;
import use_case.join_club.JoinClubDataAccessInterface;
import use_case.my_clubs.MyClubsDataAccessInterface;
import use_case.show_Notes.ShowNotesDataAccessInterface;
import use_case.show_books.ShowBooksDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing BookClub data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryBookClubDataAccessObject implements
        JoinClubDataAccessInterface, AddMessageDataAccessInterface, MyClubsDataAccessInterface,
        ExitClubDataAccessInterface,
        BookClubDataAccessInterface, ShowNotesDataAccessInterface, CreateClubDataAccessInterface,
        ShowBooksDataAccessInterface {


    private final Map<String, BookClub> bookClubMap;
    private String currentClub;
    private String currentDiscussion;

    public InMemoryBookClubDataAccessObject(Map<String, BookClub> bookClubMap) {
        this.bookClubMap = bookClubMap;
    }

    @Override
    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    @Override
    public String getCurrentNote() {
        return currentDiscussion;
    }

    @Override
    public void addUser(String username, String clubName) {
        bookClubMap.get(clubName).addMember(username);
    }

    @Override
    public void addClub(String clubName, String ClubDes) {
        bookClubMap.put(clubName, new BookClub(clubName, ClubDes));
    }

    @Override
    public boolean clubExists(String clubName) {
        return bookClubMap.containsKey(clubName);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        return bookClubMap.get(clubName).getMembers().contains(username);
    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }

    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }

    @Override
    public void saveMessage(String text, String currentUsername) {
        bookClubMap.get(currentClub).getNotes().get(currentDiscussion)
                .addMessage(new Message(currentUsername, text));
    }

    @Override
    public List<AbstractMap.SimpleEntry<String, String>> getMessages() {
        final List<Message> messagesList = bookClubMap.get(currentClub)
                .getNotes().get(currentDiscussion).getMessages();

        final List<AbstractMap.SimpleEntry<String, String>> result = new ArrayList<>();

        for (Message message: messagesList) {
            result.add(new AbstractMap.SimpleEntry<>(message.getUsername(), message.getText()));
        }
        return result;
    }

    @Override
    public List<String> getNotesTopics() {
        final Set<String> keys = bookClubMap.get(currentClub).getNotes().keySet();
        return new ArrayList<>(keys);
    }

    @Override
    public void setCurrentNote(String currentNote) {
        this.currentDiscussion = currentNote;
    }

    @Override
    public Map<String, String> getMyClubs(String currentUsername) {
        final Map<String, String> result = new HashMap<>();
        for (BookClub bookClub : bookClubMap.values()) {
            if (bookClub.isMember(currentUsername)) {
                result.put(bookClub.getName(), bookClub.getDescription());
            }
        }
        return result;
    }

    @Override
    public void removeUser(String userName, String clubName) {
        bookClubMap.get(clubName).removeMember(userName);
        JacksonTranslator.saveBookClubData(bookClubMap);
    }

    @Override
    public List<String> getBooks(String clubName) {
        final List<String> title = new ArrayList<>();
        final BookClub bookClub = bookClubMap.get(clubName);
        final List<Book> book = bookClub.getBooks();
        for (Book books : book) {
            title.add(books.getTitle());
        }
        return title;
    }
}
