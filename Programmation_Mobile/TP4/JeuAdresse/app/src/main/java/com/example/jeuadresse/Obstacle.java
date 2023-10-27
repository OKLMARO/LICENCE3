package com.example.jeuadresse;

import android.graphics.Color;
import android.graphics.Paint;

public class Obstacle extends Element{
    public Obstacle(){
        super(new float[2], 10, new Paint());
        this.paint.setColor(Color.BLACK);
    }
}
