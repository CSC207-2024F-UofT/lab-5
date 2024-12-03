package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Bookmark;
import entity.Translate;
import entity.User;
import use_case.bookmark.BookmarkUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.history.HistoryUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.translator.TranslatorUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        TranslatorUserDataAccessInterface,
        HistoryUserDataAccessInterface,
        BookmarkUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<Bookmark>> userBookmarks = new HashMap<>();
    private final Map<String, Translate> translates = new HashMap<>();
    private final Map<User, List<Translate>> userTranslates = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void saveTranslation(Translate translation) {
        translates.put(translation.getInputText(), translation);
        List<Translate> list = userTranslates.get(get(currentUsername));
        if (list == null) {
            list = new ArrayList<>();
            userTranslates.put(get(currentUsername), list);
        }
        list.add(translation);
    }

    @Override
    public Translate getTranslation(String text) {
        return translates.get(text);
    }

    @Override
    public void deleteTranslation(String text) {
        translates.remove(text);
    }

    @Override
    public List<Translate> getAllTranslationsByUser(User user) {
        return userTranslates.getOrDefault(user, new ArrayList<>());
    }

    @Override
    public Translate getMostRecentTranslation(User user) {
        return userTranslates.get(user).get(0);
    }

    @Override
    public boolean existsByText(String text) {
        return users.containsKey(text);
    }

    @Override
    public void updateTranslation(User user, Translate updatedTranslation) {
        translates.put(updatedTranslation.getInputText(), updatedTranslation);
    }

    @Override
    public void clearHistory(User user) {
        translates.clear();
        userTranslates.put(user, new ArrayList<>());
    }

    @Override
    public void deleteTranslationHistory(User user, Translate translate) {
        final String inputText = translate.getInputText();
        if (existsByText(inputText)) {
            translates.remove(inputText);
            userTranslates.get(user).remove(translate);
        }
        else {
            System.out.println("Selected history doesn't exist.");
        }
    }

    @Override
    public void addBookmark(Bookmark bookmark) {
        userBookmarks.computeIfAbsent(bookmark.getUsername(), mark -> new ArrayList<>()).add(bookmark);
    }

    @Override
    public void removeBookmark(Bookmark bookmark) {
        final List<Bookmark> bookmarks = userBookmarks.get(bookmark.getUsername());
        if (bookmarks != null) {
            bookmarks.removeIf(mark -> mark.getTranslationId().equals(bookmark.getTranslationId()));
        }
    }

    @Override
    public List<Bookmark> getBookmarksByUser(String username) {
        return List.of();
    }

}
