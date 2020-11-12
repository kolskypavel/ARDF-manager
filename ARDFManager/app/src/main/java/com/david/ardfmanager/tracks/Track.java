package com.david.ardfmanager.tracks;

import com.david.ardfmanager.ControlPoint;
import com.david.ardfmanager.competitors.Competitor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Track {

    private String name;
    private int length;

    //private ArrayList<Competitor> competitors = new ArrayList<Competitor>();
    private ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();

    public Track(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public JSONObject trackToJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        return json;
    }


        /*
    public void pridejKontrolu(Kontrola k) {
        kontroly.add(k);
    }

    public void odeberKontrolu(Kontrola k) {
        if (kontroly.contains(k)) {
            kontroly.remove(k);
        } else {
            System.out.println("Kontrola neexistuje");
        }

    }*/
}