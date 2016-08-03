package goldteam.meetup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldteam.meetup.R;

/**
 * Created by Dylan on 8/2/2016.
 */
public class BlogFragment extends android.app.Fragment {

    private TextView tv_title, tv_date, tv_blog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        initViews(view);
        showBlog();
        return view;
    }

    private void initViews(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_blog = (TextView) view.findViewById(R.id.tv_blog);

    }

    private void showBlog() {
        tv_title.setText("Test Title");
        tv_date.setText("00/00/00");
        tv_blog.setText("Blog test goes here. This is a blog");
    }
}
