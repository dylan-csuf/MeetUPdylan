package goldteam.meetup.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import goldteam.meetup.R;
import goldteam.meetup.RequestInterface;
import goldteam.meetup.statics.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c on 8/3/2016.
 */
public class AddFriendFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences pref;
    private AppCompatButton btn_add_friend;
    private EditText et_email;
    private TextView tv_back;
    private ProgressBar progress;

    public class AddFriendRequest{
        private String id;
        private String email;
        private String operation;

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        pref = getActivity().getPreferences(0);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        btn_add_friend = (AppCompatButton) view.findViewById(R.id.btn_add_friend);
        tv_back = (TextView) view.findViewById(R.id.tv_heart);
        et_email = (EditText) view.findViewById(R.id.et_email);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        btn_add_friend.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_friend:

                String email = et_email.getText().toString();

                if(!email.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    addFriendProcess(email);

                } else {

                    Snackbar.make(getView(), "Field is empty !", Snackbar.LENGTH_LONG).show();
                }
                break;

            case R.id.tv_heart:
                goToFriends();

        }
    }

    public void addFriendProcess(String email){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        final AddFriendRequest addFriendRequest = new AddFriendRequest();
        addFriendRequest.setId(pref.getString(Constants.UNIQUE_ID,""));
        addFriendRequest.setOperation(Constants.SEND_FRIEND_REQUEST);
        addFriendRequest.setEmail(email);
    }

    public void goToFriends(){
        Fragment friend = new FriendsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,friend);
        Log.i(this.toString(),"gotoFriends");
        ft.commit();
    }
}
