package pe.smarttech.ogame.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pe.smarttech.ogame.R;
import pe.smarttech.ogame.model.Planet;

public class PlanetsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Planet> planets;

    public PlanetsAdapter(ArrayList<Planet> _planets,Context _context){
        this.context = _context;
        this.planets = _planets;
    }


    @Override
    public int getCount() {
        return planets.size();
    }

    @Override
    public Object getItem(int i) {
        return planets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = layoutInflater.inflate(R.layout.listview_planets,viewGroup,false);
        TextView name = item.findViewById(R.id.name);
        TextView coords = item.findViewById(R.id.coords);
        TextView namemoon = item.findViewById(R.id.namemoon);
        TextView size = item.findViewById(R.id.size);
        LinearLayout moonview = item.findViewById(R.id.viewmoon);

        name.setText(planets.get(i).getName());
        coords.setText(planets.get(i).getCoords());

        if(planets.get(i).getMoon()!=null){
            namemoon.setText(planets.get(i).getMoon().getName());
            size.setText(planets.get(i).getMoon().getSize());
        }
        else{
            moonview.setVisibility(View.GONE);
        }
        return item;
    }
}
