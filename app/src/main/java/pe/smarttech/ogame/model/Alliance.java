package pe.smarttech.ogame.model;

import android.util.Log;

public class Alliance {
    private String id="";
    private String name="";
    private String tag="";
    public Alliance(String _id,String _name,String _tag){
        this.id=_id;
        this.name=_name;
        this.tag=_tag;
        Log.d("Alliance",id+" "+name+" "+tag);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
