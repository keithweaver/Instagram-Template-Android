package ca.keithweaver.openinstagram.Model.ServerResponse;

import java.util.ArrayList;

/**
 * Created by keithweaver on 16-08-11.
 */
public class ResLogIn {
    int success;
    String message;
    ArrayList<String> usernames;

    public ResLogIn(int success, String message, ArrayList<String> usernames) {
        this.success = success;
        this.message = message;
        this.usernames = usernames;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }
}
