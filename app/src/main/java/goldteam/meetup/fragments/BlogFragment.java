package goldteam.meetup.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldteam.meetup.R;
import goldteam.meetup.RequestInterface;
import goldteam.meetup.entities.Blog;
import goldteam.meetup.statics.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dylan on 8/2/2016.
 */
public class BlogFragment extends android.app.Fragment {



    public class BlogRequest{
        private String id;
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
    }


    private SharedPreferences pref;
    private TextView tv_title, tv_date, tv_blog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        initViews(view);
        pref = getActivity().getPreferences(0);
        showBlog();
        return view;
    }

    private void initViews(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_blog = (TextView) view.findViewById(R.id.tv_blog);

    }

    private void showBlog() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        final BlogRequest blogRequest = new BlogRequest();
        // TODO BE ABLE TO SET ID OF OTHER PEOPLE blogRequest.setId();
        blogRequest.setOperation(Constants.GET_BLOGS);
        Call<Blog> response = requestInterface.getBlog(blogRequest);

        tv_title.setText("Test Title");
        tv_date.setText("00/00/00");
        tv_blog.setText("Blog test goes here. This is a blog");
    }
}
