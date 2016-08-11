package ca.keithweaver.openinstagram.Model.ServerResponse;

/**
 * Created by keithweaver on 16-08-11.
 */
public class ResGeneral {
    int success;
    String message;

    public ResGeneral(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
