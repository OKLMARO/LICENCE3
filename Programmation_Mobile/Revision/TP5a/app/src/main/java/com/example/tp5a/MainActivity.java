package com.example.tp5a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Plateau plateau = new Plateau(getApplicationContext());
        setContentView(plateau);
    }
}