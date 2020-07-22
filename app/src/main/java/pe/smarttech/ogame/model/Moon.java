package pe.smarttech.ogame.model;

import android.util.Log;

public class Moon {
    private String id="";
    private String name="";
    private String size="";
    public Moon(String _id,String _name,String _size){
        this.id = _id;
        this.name = _name;
        this.size = _size;
        Log.d("Moon ",id+" "+name+" "+size);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSize() {
        return size;
    }
}
