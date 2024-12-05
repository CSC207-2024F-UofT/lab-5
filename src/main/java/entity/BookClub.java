package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A class representing a book club.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookClub {

    private String name;
    private String description;
    private List<String> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private Map<String, Notes> notes = new HashMap<>();

    // No-argument constructor
    public BookClub() {
    }

    // Parameterized constructor
    public BookClub(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Map<String, Notes> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, Notes> notes) {
        this.notes = notes;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * Adds username to members.
     * @param username to be added to members
     */
    public void addMember(String username) {
        this.members.add(username);
    }

    /**
     * Adds a new Note to Notes.
     * @param topic of the Note
     * @param note to be added
     */
    public void addNotes(String topic, Notes note) {
        this.notes.put(topic, note);
    }

    /**
     * Gets the Note with corresponding topic.
     * @param topic the topic of the Note
     * @return a Note
     */
    public Notes getNote(String topic) {
        return this.notes.get(topic);
    }

    /**
     * Adds a new book to books.
     * @param book to be added
     */
    public void addBook(Book book) {
        this.books.add(book);
    }

    /**
     * Returns true if the username is in members, false otherwise.
     * @param username the username
     * @return if members contains username
     */
    public Boolean isMember(String username) {
        return members.contains(username);
    }

    /**
     * Removes userName from the list.
     * @param userName to removed.
     */
    public void removeMember(String userName) {
        this.members.remove(userName);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
