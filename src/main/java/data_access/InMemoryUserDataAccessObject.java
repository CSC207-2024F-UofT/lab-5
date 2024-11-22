package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.CommonUser;
import entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final MongoCollection<Document> userCollection;
    private String currentUsername;

    public InMemoryUserDataAccessObject() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("TranslateApp");
        userCollection = database.getCollection("users");
    }

    @Override
    public boolean existsByName(String username) {
        Document userDoc = userCollection.find(Filters.eq("username", username)).first();
        return userDoc != null;
    }

    @Override
    public void save(User user) {
        Document userDoc = new Document("username", user.getName())
                .append("password", user.getPassword())
                .append("language", user.getLanguage())
                .append("friends", user.getFriends()); // Add the friends list
        userCollection.insertOne(userDoc);
    }

    @Override
    public User get(String username) {
        Document userDoc = userCollection.find(Filters.eq("username", username)).first();
        if (userDoc == null) {
            return null; // Return null or throw an exception if the user is not found
        }
        return new CommonUser(
                userDoc.getString("username"),
                userDoc.getString("password"),
                userDoc.getString("language"),
                userDoc.getList("friends", String.class) // Retrieve the friends list
        );
    }

    @Override
    public void changePassword(User user) {
        Document updatedUserDoc = new Document("username", user.getName())
                .append("password", user.getPassword())
                .append("language", user.getLanguage())
                .append("friends", user.getFriends());
        userCollection.replaceOne(Filters.eq("username", user.getName()), updatedUserDoc);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
