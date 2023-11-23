package com.example.tp5a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Plateau extends View {

    int positionBlue, positionRed;

    public Plateau(Context context) {
        super(context);
        positionRed = 0;
        positionBlue = 5;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, paint);
        paint.setStyle(Paint.Style.STROKE);
        Paint paintred = new Paint();
        Paint paintblue = new Paint();
        float[][] computes = computeCenters();
        for (int i = 0; i < 10; i++) {
            if (i == positionRed){
                paintred.setStyle(Paint.Style.FILL);
                paintred.setColor(Color.RED);
                canvas.drawCircle(computes[i][0], computes[i][1], computeRadius(), paintred);
            } else if (i == positionBlue) {
                paintblue.setStyle(Paint.Style.FILL);
                paintblue.setColor(Color.BLUE);
                canvas.drawCircle(computes[i][0], computes[i][1], computeRadius(), paintblue);
            } else{
                canvas.drawCircle(computes[i][0], computes[i][1], computeRadius(), paint);
            }
        }
    }

    private float[][] computeCenters() {
        float[][] centers = new float[10][2];
        float width = getWidth();
        float height = getHeight();
        float radius = Math.min(width, height) / 4;
        float centerX = width / 2;
        float centerY = height / 2;

        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 2 + 2 * i * Math.PI / 10;
            centers[i][0] = (float) (centerX + Math.cos(angle) * radius);
            centers[i][1] = (float) (centerY - Math.sin(angle) * radius);
        }

        return centers;
    }

    private float computeRadius() {
        return Math.min(getWidth(), getHeight()) / 20.0f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getY() > this.getHeight() / 2){
                    if(positionBlue == 9){
                        positionBlue = 0;
                        if(positionRed == positionBlue){
                            Toast.makeText(this.getContext(), "Les Démocrates contrôlent le Sénat !", Toast.LENGTH_SHORT).show();
                            positionRed = 0;
                            positionBlue = 5;
                        }
                    } else{
                        positionBlue = positionBlue + 1;
                        if(positionRed == positionBlue){
                            Toast.makeText(this.getContext(), "Les Démocrates contrôlent le Sénat !", Toast.LENGTH_SHORT).show();
                            positionRed = 0;
                            positionBlue = 5;
                        }
                    }
                    invalidate();
                } else if(event.getY() < this.getHeight() / 2) {
                    if(positionRed == 9){
                        positionRed = 0;
                        if(positionRed == positionBlue){
                            Toast.makeText(this.getContext(), "Les Républicains contrôlent le Sénat !", Toast.LENGTH_SHORT).show();
                            positionRed = 0;
                            positionBlue = 5;
                        }
                    } else{
                        positionRed = positionRed + 1;
                        if(positionRed == positionBlue){
                            Toast.makeText(this.getContext(), "Les Républicains contrôlent le Sénat !", Toast.LENGTH_SHORT).show();
                            positionRed = 0;
                            positionBlue = 5;
                        }
                    }
                    invalidate();
                }
        }

        return super.onTouchEvent(event);
    }
}
