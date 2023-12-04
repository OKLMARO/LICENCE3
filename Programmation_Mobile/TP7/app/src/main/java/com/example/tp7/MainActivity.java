package com.example.tp7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paturage paturage = new Paturage(getApplicationContext());
        paturage.ajouterMouton(new Mouton(27, 75));
        paturage.ajouterMouton(new Mouton(65, 65));
        paturage.ajouterMouton(new Mouton(80, 30));
        paturage.ajouterLoup(new Loup(50, 40));
        setContentView(paturage);
    }
}