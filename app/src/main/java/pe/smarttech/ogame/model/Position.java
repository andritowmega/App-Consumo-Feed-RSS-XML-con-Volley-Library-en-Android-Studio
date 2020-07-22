package pe.smarttech.ogame.model;

import android.util.Log;

public class Position {
    private String type="";
    private String score="";
    private String value="";
    public Position(String _type,String _score,String _value){
        this.type = _type;
        this.score = _score;
        this.value = _value;
        Log.d("Position: ",type+" "+score+" "+value);
    }

    public String getScore() {
        return score;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
