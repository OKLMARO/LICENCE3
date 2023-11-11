package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue queue;

    Response.Listener<JSONArray> arrayListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    Meteo meteo = Meteo.JsonToMeteo(response.getJSONObject(i));
                } catch (JSONException e){
                    Toast.makeText(getApplicationContext(), "Error in parsing", Toast.LENGTH_SHORT).show();
                    Log.e("parsing", "In Json parsing : " + e.getMessage());
                }
            }
        }
    };

    Response.ErrorListener arrayErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Error Network", Toast.LENGTH_SHORT).show();
            Log.e("Network", "In Network : " + error.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recyclerView);

        JsonArrayRequest arrayRequest = new JsonArrayRequest("https://lacl.fr/julien.grange/Enseignements/Programmation_mobile/23_24/TP6/fakeweather.php", arrayListener, arrayErrorListener);

        queue.add(arrayRequest);
    }
}