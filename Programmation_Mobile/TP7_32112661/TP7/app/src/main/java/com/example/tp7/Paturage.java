package com.example.tp7;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Paturage extends View {

    ArrayList<Mouton> moutons = new ArrayList<>();
    ArrayList<Loup> loups = new ArrayList<>();

    public Paturage(Context context) {
        super(context);
    }

    public void ajouterMouton(Mouton mouton){
        moutons.add(mouton);
    }

    public void ajouterLoup(Loup loup){
        loups.add(loup);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < loups.size(); i++) {
            loups.get(i).dessiner(canvas);
        }
        for (int i = 0; i < moutons.size(); i++) {
            moutons.get(i).dessiner(canvas);
        }
    }
}
