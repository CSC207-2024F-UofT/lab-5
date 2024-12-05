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
 * DAO representing book club data.
 */
public class DBBookClubDataAccessObject implements
        JoinClubDataAccessInterface, ExitClubDataAccessInterface,
        BookClubDataAccessInterface, AddMessageDataAccessInterface,
        ShowNotesDataAccessInterface,
        MyClubsDataAccessInterface, CreateClubDataAccessInterface, ShowBooksDataAccessInterface {

    private Map<String, BookClub> bookClubMap;
    private String currentClub;
    private String currentNote;
    private final JacksonTranslator jacksonTranslator;

    // Constructor to initialize JSONTranslator and load clubs from the file
    public DBBookClubDataAccessObject(JacksonTranslator jacksonTranslator) {
        this.jacksonTranslator = jacksonTranslator;
        loadClubsFromFile();
    }

    private void loadClubsFromFile() {
        this.bookClubMap = JacksonTranslator.getBookClubData();
    }

    public Map<String, BookClub> getBookClubMap() {
        return bookClubMap;
    }

    @Override
    public void addUser(String username, String clubName) {
        final BookClub bookClub = bookClubMap.get(clubName);
        bookClub.addMember(username);
        JacksonTranslator.saveBookClubData(bookClubMap);
        System.out.println("You have been sucessfully been added to the bookclub");
    }

    @Override
    public void addClub(String clubName, String ClubDes) {
        bookClubMap.put(clubName, new BookClub(clubName, ClubDes));
        JacksonTranslator.saveBookClubData(bookClubMap);

    }

    @Override
    public boolean clubExists(String clubName) {
        return bookClubMap.containsKey(clubName);
    }

    @Override
    public boolean isMember(String username, String clubName) {
        final BookClub bookClub = bookClubMap.get(clubName);
        return bookClub != null && bookClub.getMembers().contains(username);
    }

    @Override
    public List<BookClub> getAllClubs() {
        return new ArrayList<>(bookClubMap.values());
    }

    @Override
    public void saveMessage(String text, String currentUsername) {
        bookClubMap.get(currentClub).getNote(currentNote).addMessage(new Message(currentUsername, text));
        JacksonTranslator.saveBookClubData(bookClubMap);
    }

    @Override
    public List<AbstractMap.SimpleEntry<String, String>> getMessages() {
        final List<Message> messagesList = bookClubMap.get(currentClub)
                .getNotes().get(currentNote).getMessages();

        final List<AbstractMap.SimpleEntry<String, String>> result = new ArrayList<>();

        for (Message message : messagesList) {
            result.add(new AbstractMap.SimpleEntry<>(message.getUsername(), message.getText()));
        }
        return result;
    }

    @Override
    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    @Override
    public String getCurrentClub() {
        return currentClub;
    }

    @Override
    public void setCurrentNote(String currentNote) {
        this.currentNote = currentNote;
    }

    @Override
    public String getCurrentNote() {
        return currentNote;
    }

    @Override
    public List<String> getNotesTopics() {
        final Set<String> keys = bookClubMap.get(currentClub).getNotes().keySet();
        return new ArrayList<>(keys);
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
