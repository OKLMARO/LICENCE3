package com.example.jeuadresse;

import android.graphics.Color;
import android.graphics.Paint;

public class Bille extends Element{
    public Bille(){
        super(new float[2], 10, new Paint());
        this.paint.setColor(Color.BLUE);
    }
}
