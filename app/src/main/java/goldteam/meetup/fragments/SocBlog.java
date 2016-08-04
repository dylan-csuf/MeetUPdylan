package goldteam.meetup.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldteam.meetup.R;

/**
 * Created by c on 8/3/2016.
 */
public class SocBlog extends Fragment{

    private SharedPreferences pref;
    private TextView tv_title, tv_date, tv_blog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.soc_blog,container,false);
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

        tv_title.setText("Hemlock sucks");
        tv_date.setText("08/01/2016 1:30am");
        tv_blog.setText("I sing the body electric,\n" +
                "The armies of those I love engirth me and I engirth them,\n" +
                "They will not let me off till I go with them, respond to them,\n" +
                "And discorrupt them, and charge them full with the charge of the soul.\n" +
                "\n" +
                "Was it doubted that those who corrupt their own bodies conceal themselves?\n" +
                "And if those who defile the living are as bad as they who defile the dead?\n" +
                "And if the body does not do fully as much as the soul?\n" +
                "And if the body were not the soul, what is the soul? ");
    }
}
