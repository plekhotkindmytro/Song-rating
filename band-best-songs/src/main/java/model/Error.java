package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Document
public class Error {
    @Id
    private String id;
    private String songTitle;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Error valueOf(String title, String message) {
        Error error = new Error();
        error.setSongTitle(title);
        error.setMessage(message);
        return error;
    }
}
