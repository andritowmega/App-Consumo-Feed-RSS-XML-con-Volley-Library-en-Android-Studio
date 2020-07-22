package pe.smarttech.ogame.model;

import android.util.Log;

public class Planet {
    private String id="";
    private String name="";
    private String coords="";
    private Moon moon;
    public Planet(String _id,String _name,String _coords,Moon _moon){
        this.id=_id;
        this.name=_name;
        this.coords=_coords;
        this.moon = _moon;
        Log.d("Planet: ",id+" "+name+" "+coords);
    }

    public String getCoords() {
        return coords;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Moon getMoon() {
        return moon;
    }
}
