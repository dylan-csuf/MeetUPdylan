package goldteam.meetup.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import goldteam.meetup.FriendListAdaptor;
import goldteam.meetup.R;
import goldteam.meetup.entities.Friend;

/**
 * Created by c on 7/31/2016.
 */
public class FriendsFragment extends Fragment {

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
        Log.i("@POST", "JSON:{\"operation\":\"get_friend_list\",\"\"id\":\"5797e1800fcdd3.11036548\"}");
        ArrayList<Friend> philosophers = new ArrayList();
        Friend satre = new Friend();
        satre.setEmail("js@lolinstitusions.com");
        satre.setId("b");
        satre.setName("Jean Paul Satre");
        philosophers.add(satre);

        Friend kierk = new Friend();
        kierk.setId("c");
        kierk.setName("Søren Kierkegaard");
        kierk.setEmail("christian@existentialism.edu");
        philosophers.add(kierk);

        Friend descartes = new Friend();
        descartes.setId("d");
        descartes.setEmail("cogito@spaghetto.net");
        descartes.setName("Rene Descartes");
        philosophers.add(descartes);

        Friend witt = new Friend();
        witt.setId("e");
        witt.setEmail("Tractatus@Logico.edu");
        witt.setName("Ludwig Wittgenstein");
        philosophers.add(witt);

        Friend kant = new Friend();
        kant.setId("f");
        kant.setEmail("act_according_to@maxim.com");
        kant.setName("Immanuel Kant");
        philosophers.add(kant);

        Friend marx = new Friend();
        marx.setEmail("RichardStallman@gnu.org");
        marx.setId("g");
        marx.setName("Karl Marx");
        philosophers.add(marx);

        Log.d(String.valueOf(philosophers.size()), "sizeof Friends");

        ListAdapter customAdapter = new FriendListAdaptor(getActivity(), R.layout.item_listrow, philosophers);
        if(listView == null){
            Log.e("wtf", "listview = null");
        }else {

            listView.setAdapter(customAdapter);

        }

    }
}
