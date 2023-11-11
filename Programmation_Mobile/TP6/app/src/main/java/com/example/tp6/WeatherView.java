package com.example.tp6;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class WeatherView extends ConstraintLayout {

    private TextView villeView, tempView, ventView;
    private ImageView imageView;
    private final static int margin = 30;

    public WeatherView(Context context) {
        super(context);
        villeView = new TextView(context);
        tempView = new TextView(context);
        ventView = new TextView(context);

        villeView.setId(View.generateViewId());
        tempView.setId(View.generateViewId());
        ventView.setId(View.generateViewId());
        this.setId(View.generateViewId());

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);

        constraintSet.connect(villeView.getId(), ConstraintSet.LEFT, this.getId(), ConstraintSet.LEFT, margin);
        constraintSet.connect(villeView.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, margin);

        constraintSet.connect(tempView.getId(), ConstraintSet.LEFT, villeView.getId(), ConstraintSet.RIGHT, margin);
        constraintSet.connect(tempView.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, margin);

        constraintSet.connect(ventView.getId(), ConstraintSet.LEFT, tempView.getId(), ConstraintSet.RIGHT, margin);
        constraintSet.connect(ventView.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, margin);

        constraintSet.applyTo(this);
    }

    public void setMeteo(Meteo meteo){
        if (meteo.pluie == true) {
            tempView.setBackgroundColor(Color.BLUE);
        }
        else {
            tempView.setBackgroundColor(Color.GRAY);
        }
    }
}
