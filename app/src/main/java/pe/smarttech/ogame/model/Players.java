package pe.smarttech.ogame.model;

import androidx.annotation.NonNull;

public class Players {
    Integer id;
    String name;

    public Players(Integer _id,String _name){
        this.id = _id;
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

}
