package goldteam.meetup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goldteam.meetup.R;

/**
 * Created by c on 8/3/2016.
 */
public class BlankBlogFragmentNF extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.blank_blog_fragment,container,false);
        setHasOptionsMenu(false);
        return view;
    }
}
