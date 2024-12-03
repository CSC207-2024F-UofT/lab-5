package org.example;

import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonValue;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.*;
public class QuickStart {
    public static void main( String[] args ) {
        // first we get the uri so we can connect to our database
        String uri = "mongodb+srv://andersonwyatt199:oSJAGafbowj9jeFl@cluster0.j9lgn.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        // Now we try to set mongoClient object to our uri
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // We access the specific database
            MongoDatabase database = mongoClient.getDatabase("Movies4U");
            // Inside the database we have collections, right now we access our movies collection
            MongoCollection<Document> collection = database.getCollection("movies");
            // Create a list to store genre for this specific movie
            ArrayList<String> genres = new ArrayList<>(Arrays.asList("sci-fi", "action"));
            ArrayList<Map<String,String>> reviews = new ArrayList<>();
            // A map object to store reviews
            Map<String, String> review1 = new HashMap<>();
            review1.put("name", "joe");
            review1.put("body", "decent movie");
            review1.put("date", "sept 3 2024");
            reviews.add(review1);
            // now this is the document that we insert
            Document test = new Document("id", new ObjectId())
                    .append("title", "Terminator")
                    .append("length", "2 hours")
                    .append("genre", genres)
                    .append("reviews", reviews);
            // Get back the unique id key and store it somewhere so we can access it.
            InsertOneResult result = collection.insertOne(test);
            BsonValue id = result.getInsertedId();
            System.out.println(id);
        }
    }
}