package goldteam.meetup;

/**
 * Created by c on 7/31/2016.
 */
public class FriendListRequest {
    private String id;
    private String operation;

    public String getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setId(String id) {
        this.id = id;
    }
}
