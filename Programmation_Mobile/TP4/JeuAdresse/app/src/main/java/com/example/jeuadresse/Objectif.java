package com.example.jeuadresse;

import android.graphics.Color;
import android.graphics.Paint;

public class Objectif extends Element{
    public Objectif(){
        super(new float[2], 10, new Paint());
        this.paint.setColor(Color.GREEN);
    }
}
