package data_access;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import use_case.search.SearchDataAccessInterface;

/**
 * Implementation of the search data access interface.
 */
public class OpenLibraryClient implements SearchDataAccessInterface {
    private static final String OPEN_LIBRARY_API_SEARCH_URL = "https://openlibrary.org/search.json?title=";
    private static final String OPEN_LIBRAY_API_COVER_URL = "https://covers.openlibrary.org/b/olid/";
    private final Gson gson;

    public OpenLibraryClient() {
        this.gson = new Gson();
    }

    @Override
    public List<BookDataTransferObject> searchBookByTitle(String title) {

        final List<BookDataTransferObject> books = new ArrayList<>();

        String newTitle = "";
        if (title.length() > 1) {
            final String[] wordList = title.split(" ");
            newTitle = newTitle + wordList[0];
            final int n = wordList.length;
            for (int i = 1; i < n; i++) {
                newTitle = newTitle + "+" + wordList[i];
            }
        }
        else if (title.length() == 1) {
            newTitle = newTitle + title;
        }

        try {
            final URL url = new URL(OPEN_LIBRARY_API_SEARCH_URL + newTitle);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                final JsonObject responseJSON = gson.fromJson(reader, JsonObject.class);
                final JsonArray docs = responseJSON.getAsJsonArray("docs");

                if (!docs.isEmpty()) {
                    final JsonObject bookDetails = docs.get(0).getAsJsonObject();
                    final BookDataTransferObject book = parseBookFromJson(bookDetails, title);
                    books.add(book);
                }
                else {
                    final BookDataTransferObject newBook = new BookDataTransferObject(title,
                            "unknown author", "unknown key", "");
                    books.add(newBook);
                }
            }
        }
        catch (IOException exceptionInputOutput) {
            exceptionInputOutput.getCause();
            exceptionInputOutput.getMessage();
        }
        return books;
    }

    private BookDataTransferObject parseBookFromJson(JsonObject bookJson, String bookTitle) {
        final String titre = bookTitle;
        final String auteur;
        final String numero;
        final String couverture;

        if (bookJson.has("author_name")) {
            final JsonArray authors = bookJson.getAsJsonArray("author_name");
            auteur = authors.get(0).getAsString();
        }
        else {
            auteur = "Unknown author";
        }

        if (bookJson.has("cover_edition_key")) {
            numero = bookJson.get("cover_edition_key").getAsString();
            couverture = OPEN_LIBRAY_API_COVER_URL + numero + "-M.jpg";
        }
        else {
            numero = "Unknown  key";
            couverture = "";
        }

        return new BookDataTransferObject(titre, auteur, numero, couverture);
    }

}
