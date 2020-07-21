package pe.smarttech.ogame.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.smarttech.ogame.R;
import pe.smarttech.ogame.model.Players;

public class AutcompleteAdapter extends BaseAdapter {
    ArrayList<Players> players;
    Context context;
    public AutcompleteAdapter(ArrayList<Players> _players,Context _context){
        this.players = _players;
        this.context = _context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = layoutInflater.inflate(R.layout.autcomplete,parent,false);
        TextView name = item.findViewById(R.id.name);
        name.setText(players.get(position).getName());
        return item;
    }

}
