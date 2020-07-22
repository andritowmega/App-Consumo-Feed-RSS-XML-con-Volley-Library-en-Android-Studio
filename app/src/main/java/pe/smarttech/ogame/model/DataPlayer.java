package pe.smarttech.ogame.model;

import java.util.ArrayList;

public class DataPlayer {
    private ArrayList<Position> positions;
    private ArrayList<Planet> planets;
    private String ships="";
    private String name="";
    private Alliance alliance;
    public DataPlayer(String _name,String _ships,ArrayList<Position> _positions,ArrayList<Planet> _planets,Alliance _alliance){
        this.positions=_positions;
        this.planets=_planets;
        this.ships=_ships;
        this.name=_name;
        this.alliance=_alliance;
    }

    public String getName() {
        return name;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public String getShips() {
        return ships;
    }
}
