package com.example.tp6;

import org.json.JSONException;
import org.json.JSONObject;

public class Meteo {

    boolean pluie;
    String ville;
    double temperature;
    double vent_vitesse;
    String vent_direction;

    public Meteo(boolean pluie, String ville, double temperature, double vent_vitesse, String vent_direction) {
        this.pluie = pluie;
        this.ville = ville;
        this.temperature = temperature;
        this.vent_vitesse = vent_vitesse;
        this.vent_direction = vent_direction;
    }

    public static Meteo JsonToMeteo(JSONObject o){
        try {
            String ville = o.getString("ville");
            double temperature = o.getDouble("température");
            boolean pluie = o.getBoolean("pluie");
            double vent_vitesse = o.getJSONObject("vent").getDouble("vitesse");
            String vent_direction = o.getJSONObject("vent").getString("orientation");

            return new Meteo(pluie, ville, temperature, vent_vitesse, vent_direction);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
