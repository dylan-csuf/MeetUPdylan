package goldteam.meetup.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goldteam.meetup.R;

/**
 * Created by c on 8/3/2016.
 */
public class NewBlogPost extends Fragment{

    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        pref = getActivity().getPreferences(0);

        return view;
    }
}
