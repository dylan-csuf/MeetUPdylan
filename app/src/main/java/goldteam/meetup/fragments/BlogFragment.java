package goldteam.meetup.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldteam.meetup.R;
import goldteam.meetup.debug.Nietzsche;

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

        Nietzsche nietzsche = new Nietzsche();
        tv_title.setText(nietzsche.getTitle());
        tv_date.setText(nietzsche.getDatetime());
        tv_blog.setText(nietzsche.getBody());
    }
}
