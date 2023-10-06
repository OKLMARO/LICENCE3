package com.example.dessin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Dessin dessin;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = new ConstraintLayout(getApplicationContext());
        if (savedInstanceState != null) {
            int color = savedInstanceState.getInt("color");
            ArrayList<Cercle> cercles = savedInstanceState.getParcelableArrayList("dessins", Cercle.class);
            dessin = new Dessin(getApplicationContext(), color, cercles);
        }
        else {
            dessin = new Dessin(getApplicationContext(), Color.BLACK);
        }
        ColorDialog colorDialog = new ColorDialog(dessin);
        Button button = new Button(getApplicationContext());
        button.setText("Set Color");
        button.setOnClickListener(view -> colorDialog.show(getSupportFragmentManager(), "color dialog"));
        layout.addView(dessin);
        layout.addView(button);
        setContentView(layout);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("color", dessin.getCouleur());
        outState.putParcelableArrayList("dessins", dessin.getCercles());
        super.onSaveInstanceState(outState);
    }
}