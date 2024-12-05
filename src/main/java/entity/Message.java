package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Message class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String username;
    private String text;

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
    }

    // Default constructor (required for deserialization)
    public Message() {

    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message from" + getUsername() + ":" + text;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }
}
