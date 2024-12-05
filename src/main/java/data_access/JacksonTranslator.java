
package data_access;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entity.BookClub;

/**
 * Converts between a Map of BookClub objects and JSON data.
 */
public class JacksonTranslator {
    private static final String FILE_PATH = "data/book_clubs.json";

    /**
     * Translates JSON book club data into a map of book club names to corresponding BookClub objects.
     * @return Map of book club objects
     */
    public static Map<String, BookClub> getBookClubData() {
        Map<String, BookClub> result = new HashMap<>();
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            // Load JSON file from the classpath (resources folder)
            final InputStream inputStream = new FileInputStream(new File(FILE_PATH));

            if (inputStream == null) {
                System.err.println("Error: book_clubs.json file not found in resources.");
            }
            else {
                // Read JSON data into a list of BookClub objects
                result = objectMapper.readValue(inputStream, new TypeReference<Map<String, BookClub>>() { });
            }
        }
        catch (IOException ioException) {
            System.err.println("Error reading or parsing the JSON file: " + ioException.getMessage());
            ioException.printStackTrace();
        }
        return result;
    }

    /**
     * Saves book club map in JSON format.
     * @param bookClubMap map of BookClub objects
     * @throws RuntimeException a runtime exception
     */
    public static void saveBookClubData(Map<String, BookClub> bookClubMap) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(FILE_PATH), bookClubMap);
            System.out.println("Book club data successfully saved to " + FILE_PATH);

        }
        catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}
