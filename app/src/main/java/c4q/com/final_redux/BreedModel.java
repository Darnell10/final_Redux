package c4q.com.final_redux;

/**
 * Created by D on 2/25/18.
 */

public class BreedModel {

    private String status;
    private String message;

    public BreedModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
