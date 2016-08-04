package goldteam.meetup.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import goldteam.meetup.FriendListAdaptor;
import goldteam.meetup.R;
import goldteam.meetup.entities.Friend;

/**
 * Created by c on 7/31/2016.
 */
public class PendingFriendsFragment extends Fragment {

    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends,container,false);
        Log.d("View", view.toString());
        pref = getActivity().getPreferences(0);
        initViews(view);
        return view;
    }


    private void initViews(View view){
        ListView listView = (ListView) view.findViewById(R.id.listView_friends);

        getFriendList(listView);
    }

    private void getFriendList(ListView listView){
        Log.i("@POST", "JSON:{\"operation\":\"get_pending_list\",\"\"id\":\"5797e1800fcdd3.11036548\"}");
        ArrayList<Friend> philosophers = new ArrayList();

        Friend lol = new Friend();
        lol.setEmail("lol");
        philosophers.add(lol);

        Friend socrates = new Friend();
        socrates.setEmail("sophistry@philosophicalNullPointer.net");
        socrates.setId("a");
        socrates.setName("Socrates");
        philosophers.add(socrates);

        Friend aristotle = new Friend();
        aristotle.setEmail("lol@nannystate.gov");
        aristotle.setId("b");
        aristotle.setName("Aristotle");
        philosophers.add(aristotle);

        Friend plato = new Friend();
        plato.setEmail("allegory@thecave.net");
        plato.setId("a");
        plato.setName("Plato");
        philosophers.add(plato);



        Log.d(String.valueOf(philosophers.size()), "sizeof Friends");

        ListAdapter customAdapter = new FriendListAdaptor(getActivity(), R.layout.item_listrow, philosophers);
        if(listView == null){
            Log.e("wtf", "listview = null");
        }else {

            listView.setAdapter(customAdapter);

        }

        assert listView != null;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    Log.d("lol", "xD");

                }
            }
        });
    }
}
