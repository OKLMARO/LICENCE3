package com.example.tp7;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Animal {
    int x, y;
    int couleur;

    public Animal(int x, int y, int couleur){
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    public void dessiner(Canvas canvas){
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        Paint paint = new Paint();
        paint.setColor(couleur);
        canvas.drawCircle((x/100)*w, (y/100)*h, Math.min(w, h)/20, paint);
    }
}
