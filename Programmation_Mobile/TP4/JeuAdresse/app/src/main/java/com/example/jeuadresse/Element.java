package com.example.jeuadresse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Element {
    float[] centre = new float[2];
    float rayon;
    Paint paint;

    public Element(float[] center, float rayon, Paint paint) {
        this.centre = center;
        this.rayon = rayon;
        this.paint = paint;
    }

    public float[] getCentre() {
        return centre;
    }

    public void setCentre(float[] centre) {
        this.centre = centre;
    }

    public float getRayon() {
        return rayon;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(this.centre[0], this.centre[1], this.rayon, this.paint);
    }

    public boolean chevauche(Element element){
        float[] centre = element.getCentre();
        float rayon = element.getRayon();
        float distance = (float) Math.sqrt(Math.pow(this.centre[0] - centre[0], 2) + Math.pow(this.centre[1] - centre[1], 2));
        return distance < this.rayon + rayon;
    }
}
