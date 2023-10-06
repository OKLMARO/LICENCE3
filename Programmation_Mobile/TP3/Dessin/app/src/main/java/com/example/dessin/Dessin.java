package com.example.dessin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Dessin extends View {

    int couleur;
    ArrayList<Cercle> cercles;

    public ArrayList<Cercle> getCercles() {
        return cercles;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public Dessin(Context context, int couleur) {
        super(context);
        this.couleur = couleur;
        this.cercles = new ArrayList<>();
    }

    public Dessin(Context context, int couleur, ArrayList<Cercle> cercles) {
        super(context);
        this.couleur = couleur;
        this.cercles = cercles;
    }

    public void addCercle(Cercle cercle){
        cercles.add(cercle);
    }

    public int getCouleur() {
        return couleur;
    }

    int r = 1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                cercles.add(new Cercle(event.getX(), event.getY(), r, Color.BLACK));
                break;
            case MotionEvent.ACTION_UP:
                r = 1;
                break;

            case MotionEvent.ACTION_MOVE:
                r = r + 1;
                cercles.add(new Cercle(event.getX(), event.getY(), r, this.couleur));
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        for (Cercle cercle : this.cercles) {
            paint.setColor(cercle.getCouleur());
            canvas.drawCircle(cercle.cx, cercle.cy, cercle.rayon, paint);
        }
    }
}
