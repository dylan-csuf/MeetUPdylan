package goldteam.meetup.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import goldteam.meetup.FriendListAdaptor;
import goldteam.meetup.FriendListRequest;
import goldteam.meetup.R;
import goldteam.meetup.RequestInterface;
import goldteam.meetup.entities.Friend;
import goldteam.meetup.statics.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c on 7/31/2016.
 */
public class FriendsFragment extends Fragment {

    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends,container,false);
        pref = getActivity().getPreferences(0);
        initViews(view);
        return view;
    }

    private void initViews(View v){


        getFriendList();

    }

    private void getFriendList(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        final FriendListRequest friendListRequest = new FriendListRequest();
        friendListRequest.setId(pref.getString(Constants.UNIQUE_ID,""));
        friendListRequest.setOperation(Constants.GET_FRIEND_LIST);
        Call<List<Friend>> response = requestInterface.getList(friendListRequest);

        response.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                ListView listView = (ListView) getActivity().findViewById(R.id.listView_friends);
                List<Friend> friends = response.body();
                ArrayList<Friend> friendList = new ArrayList<>();

                // populate the list of friends
                for(int i =0; i < friends.size(); i++){
                    Friend friend = friends.get(i);
                    friendList.add(friend);
                }

                ListAdapter customAdapter = new FriendListAdaptor(getActivity(), R.layout.item_listrow, friendList);
                listView.setAdapter(customAdapter);

                Log.d("this", "that");
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                Log.d("damn we suck", "shieeet");
            }
        });
    }
}
