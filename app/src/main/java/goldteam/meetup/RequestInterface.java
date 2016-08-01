package goldteam.meetup;

/**
 * Created by c on 7/14/2016.
 */

import java.util.List;

import goldteam.meetup.entities.Friend;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("index.php")
    Call<ServerResponse> userOperation(@Body ServerRequest request);

    @POST("index.php")
    Call<List<Friend>> getList(@Body FriendListRequest request);

}