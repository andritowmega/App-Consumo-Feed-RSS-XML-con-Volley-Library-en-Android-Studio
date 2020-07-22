package pe.smarttech.ogame;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import pe.smarttech.ogame.Adapters.PlanetsAdapter;
import pe.smarttech.ogame.model.DataPlayer;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataPlayerFragment extends Fragment {
    DataPlayer dataPlayer;
    public DataPlayerFragment(DataPlayer _dataplayer){
        this.dataPlayer=_dataplayer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View item = inflater.inflate(R.layout.fragment_data_player, container, false);
        TextView puntos = item.findViewById(R.id.puntos);
        TextView economia = item.findViewById(R.id.economia);
        TextView investigacion = item.findViewById(R.id.investigacion);
        TextView militar = item.findViewById(R.id.militar);
        TextView militarconstruccion = item.findViewById(R.id.militarconstruccion);
        TextView militardestruccion = item.findViewById(R.id.militardestruccion);
        TextView militarperdido = item.findViewById(R.id.militarperdido);
        TextView honor = item.findViewById(R.id.honor);
        TextView ejercito = item.findViewById(R.id.ejercito);
        ListView lvplanets = item.findViewById(R.id.lv_planet);
        TextView total = item.findViewById(R.id.total);

        puntos.setText(dataPlayer.getPositions().get(0).getValue());
        economia.setText(dataPlayer.getPositions().get(1).getValue());
        investigacion.setText(dataPlayer.getPositions().get(2).getValue());
        militar.setText(dataPlayer.getPositions().get(3).getValue());
        militarconstruccion.setText(dataPlayer.getPositions().get(4).getValue());
        militardestruccion.setText(dataPlayer.getPositions().get(5).getValue());
        militarperdido.setText(dataPlayer.getPositions().get(6).getValue());
        honor.setText(dataPlayer.getPositions().get(7).getScore());
        ejercito.setText("Ejercito: "+dataPlayer.getShips()+" Naves");
        if(dataPlayer.getAlliance()!=null){
            ejercito.setText(ejercito.getText()+"\n Alianza: "+dataPlayer.getAlliance().getName()+" ("+dataPlayer.getAlliance().getTag()+")");
        }

        total.setText(dataPlayer.getPlanets().size()+" planetas");


        PlanetsAdapter planetsAdapter = new PlanetsAdapter(dataPlayer.getPlanets(),getActivity().getApplicationContext());
        if(lvplanets!=null){
            Log.d("Listview","No Esta null");
            lvplanets.setAdapter(planetsAdapter);
        }
        else{
            Log.d("Listview","Esta null");
        }
        return item;
    }


}
