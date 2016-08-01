package goldteam.meetup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import goldteam.meetup.entities.Friend;

/**
 * Created by c on 7/31/2016.
 */
public class FriendListAdaptor extends ArrayAdapter<Friend> {

    public FriendListAdaptor(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public FriendListAdaptor(Context context, int resource, List<Friend> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        Log.i(this.toString(), "getView");

        if (v == null) {
            Log.i(this.toString(), "view is null");
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_listrow, null);
        }

        Friend p = getItem(position);

        if (p != null) {

            TextView tt1 = (TextView) v.findViewById(R.id.id);
            TextView tt2 = (TextView) v.findViewById(R.id.email);
            TextView tt3 = (TextView) v.findViewById(R.id.name);

            if (tt1 != null) {
                tt1.setText(p.getId());
            }

            if (tt2 != null) {
                tt2.setText(p.getEmail());
            }

            if (tt3 != null) {
                tt3.setText(p.getName());
            }
        } else{
            Log.i(this.toString(),"p is null, wtf");
        }

        return v;
    }

}

