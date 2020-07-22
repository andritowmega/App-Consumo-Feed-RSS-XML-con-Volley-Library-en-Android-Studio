package pe.smarttech.ogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import pe.smarttech.ogame.Adapters.AutcompleteAdapter;
import pe.smarttech.ogame.model.Alliance;
import pe.smarttech.ogame.model.DataPlayer;
import pe.smarttech.ogame.model.Moon;
import pe.smarttech.ogame.model.Planet;
import pe.smarttech.ogame.model.Players;
import pe.smarttech.ogame.model.Position;

public class DataActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    String URLbase = "https://s170-es.ogame.gameforge.com/api";
    ArrayList<Players> players = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> playersName = new ArrayList<>();
    ArrayAdapter<String> adapterNames;
    ArrayList<String> playersId = new ArrayList<>();
    Fragment fragment;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mQueue = Volley.newRequestQueue(DataActivity.this);
        autoCompleteTextView = findViewById(R.id.findplayer);
        status = findViewById(R.id.status);
        status.setVisibility(View.GONE);
        GetPlayers();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Integer idplayer = GetIdPlayer(autoCompleteTextView.getText().toString());
                if(idplayer!=null){
                    Log.d("Response","ok "+idplayer);
                    GetDataPlayer(idplayer);
                }
                else{
                    Toast.makeText(DataActivity.this,"Sin id para el usuario "+autoCompleteTextView.getText().toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void GetPlayers(){
        StatusView("ESCANEANDO JUGADORES");
        StringRequest request = new StringRequest(Request.Method.GET, URLbase+"/players.xml",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                        //API to obtain DOM Document instance
                        DocumentBuilder builder = null;
                        try
                        {
                            //Create DocumentBuilder with default configuration
                            builder = factory.newDocumentBuilder();
                            //Parse the content to Document object
                            Document doc = builder.parse(new InputSource(new StringReader(response)));
                            for(int i = 0; i<doc.getFirstChild().getChildNodes().getLength();i++){
                                    Element eElement = (Element) doc.getFirstChild().getChildNodes().item(i);
                                    playersName.add(eElement.getAttribute("name"));
                                    playersId.add(eElement.getAttribute("id"));
                                    players.add(new Players(Integer.parseInt(eElement.getAttribute("id")),eElement.getAttribute("name")));
                            }
                            Log.d("Response","ok");
                            setAdapter();
                        }
                        catch (Exception e)
                        {
                            Log.d("Response","error onresponse");
                            e.printStackTrace();
                        }
                        status.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Response",error.getMessage());
                    }
                }
        );
        mQueue.add(request);
    }
    public void GetDataPlayer(Integer _idplayer){
        StatusView("ESCANEANDO "+ autoCompleteTextView.getText());
        StringRequest request = new StringRequest(Request.Method.GET, URLbase+"/playerData.xml?id="+_idplayer,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        //API to obtain DOM Document instance
                        DocumentBuilder builder = null;
                        DataPlayer dataPlayer = null;
                        try
                        {
                            //Create DocumentBuilder with default configuration
                            builder = factory.newDocumentBuilder();
                            //Parse the content to Document object
                            Document doc = builder.parse(new InputSource(new StringReader(response)));
                            //Element position = (Element) doc.getElementsByTagName("positions").getLength();
                            NodeList positions = doc.getElementsByTagName("positions");
                                Element eElement = (Element) positions.item(0);
                                NodeList position = eElement.getElementsByTagName("position");

                                String ships="";
                                String name=autoCompleteTextView.getText().toString();

                                //Creando Positions
                                ArrayList<Position> positionslist = new ArrayList<>();

                                for(int z=0;z<position.getLength();z++){
                                    Element elementposition = (Element)  position.item(z);
                                    if(elementposition.getAttribute("type").equals("3")){
                                        ships=elementposition.getAttribute("ships");
                                    }
                                    positionslist.add(new Position(elementposition.getAttribute("type"),elementposition.getAttribute("score"),elementposition.getTextContent()));
                                }

                            NodeList planets = doc.getElementsByTagName("planets");
                            Element eElemenplanet = (Element) planets.item(0);
                            NodeList planet = eElemenplanet.getElementsByTagName("planet");

                            //Creando Planets
                            ArrayList<Planet> planetslist = new ArrayList<>();

                            for(int z=0;z<planet.getLength();z++){
                                Element elementplanet = (Element)  planet.item(z);
                                //Consultamos si tiene luna
                                Moon moonitem=null;
                                if(elementplanet.getElementsByTagName("moon").getLength()>0){
                                  NodeList Moon = elementplanet.getElementsByTagName("moon");
                                  Element elementMoon = (Element) Moon.item(0);
                                  moonitem = new Moon(elementMoon.getAttribute("id"),elementMoon.getAttribute("name"),elementMoon.getAttribute("size"));
                                }
                                planetslist.add(new Planet(elementplanet.getAttribute("id"),elementplanet.getAttribute("name"),elementplanet.getAttribute("coords"),moonitem));

                            }
                            //creando Alliance
                            Alliance allianceitem = null;
                            if(doc.getElementsByTagName("alliance").getLength()>0){
                                Element alliance = (Element) doc.getElementsByTagName("alliance").item(0);
                                Element nameAlliance = (Element) alliance.getFirstChild();
                                Element tagAlliance = (Element) alliance.getElementsByTagName("tag").item(0);
                                Log.d("Name Alliance",alliance.getAttribute("id"));
                                allianceitem = new Alliance(alliance.getAttribute("id"),nameAlliance.getTextContent(),tagAlliance.getTextContent());
                            }
                            else{
                                Log.d("Alliance","Sin alianza");
                            }
                            dataPlayer = new DataPlayer(name,ships,positionslist,planetslist,allianceitem);


                        }
                        catch (Exception e)
                        {
                            Log.d("Response","error onresponse");
                            e.printStackTrace();
                        }
                        status.setVisibility(View.GONE);
                        InflateFragment(dataPlayer);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Response",error.getMessage());
                    }
                }
        );
        mQueue.add(request);
    }
    private Integer GetIdPlayer(String name){
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(name)){
                return players.get(i).getId();
            }
        }
        return null;
    }
    public void setAdapter(){

        adapterNames = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, playersName);
        autoCompleteTextView.setAdapter(adapterNames);

    }
    private void InflateFragment(DataPlayer dataPlayer){
        DataPlayerFragment dataPlayerFragment = new DataPlayerFragment(dataPlayer);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content, dataPlayerFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }
    private void StatusView(String message){
        status.setText(message);
        status.setVisibility(View.VISIBLE);
    }
}
