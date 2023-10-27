package com.example.jeuadresse;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Terrain extends View {
    Bille bille;
    Objectif objectif;
    ArrayList<Obstacle> obstacles;
    long startTime;

    public Terrain(Context context) {
        super(context);
        obstacles = new ArrayList<Obstacle>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bille != null){
            bille.draw(canvas);
        }
        if (objectif != null){
            objectif.draw(canvas);
        }
        if(obstacles != null) {
            for (Obstacle obstacle : obstacles) {
                obstacle.draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (objectif == null) {
            objectif = new Objectif();
            objectif.setCentre(new float[]{event.getX(), event.getY()});
            invalidate();
        } else if (bille == null){
            bille = new Bille();
            bille.setCentre(new float[]{event.getX(), event.getY()});
            invalidate();
        } else {
            Obstacle temp = new Obstacle();
            temp.setCentre(new float[]{event.getX(), event.getY()});
            /*switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startTime = event.getEventTime();
                    break;
                case MotionEvent.ACTION_UP:
                    long time = event.getEventTime() - startTime;
                    time = time / 500;
                    temp.setRayon(temp.getRayon() * time);
                    break;
            }*/
            obstacles.add(temp);
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    public boolean roll(float[] deplacement)  {
        if (bille != null) {
            float[] coordinates = bille.getCentre();
            if (coordinates[0] + deplacement[0] + (bille.getRayon() / 2) > getWidth() || coordinates[0] + deplacement[0] - (bille.getRayon() / 2) < 0)
                deplacement[0] = 0;
            if (coordinates[1] + deplacement[1] + (bille.getRayon() / 2) > getHeight() || coordinates[1] + deplacement[1] - (bille.getRayon() / 2) < 0)
                deplacement[1] = 0;
            coordinates[0] += deplacement[0];
            coordinates[1] += deplacement[1];
            bille.setCentre(coordinates);
            checkPosition();
            invalidate();
            return true;
        }
        return false;
    }

    public boolean checkPosition(){
        if(bille != null && objectif != null){
            if (bille.chevauche(objectif)){
                Toast.makeText(getContext(), "Gagné !", Toast.LENGTH_SHORT).show();
                bille = null;
                objectif = null;
                obstacles.clear();
                invalidate();
                return true;
            }
            for (Obstacle obstacle : obstacles){
                if (bille.chevauche(obstacle)){
                    Toast.makeText(getContext(), "Perdu !", Toast.LENGTH_SHORT).show();
                    bille = null;
                    objectif = null;
                    obstacles.clear();
                    invalidate();
                    return true;
                }
            }
        }
        return false;
    }
}
