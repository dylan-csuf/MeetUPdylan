package goldteam.meetup.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldteam.meetup.R;

/**
 * Created by Dylan on 8/2/2016.
 */
public class BlogFragment extends Fragment {

    private TextView tv_title, tv_date, tv_blog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_blog = (TextView) view.findViewById(R.id.tv_blog);

    }

    private void showBlog() {

    }
}
