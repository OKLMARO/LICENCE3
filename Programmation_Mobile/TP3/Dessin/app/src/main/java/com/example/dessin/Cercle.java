package com.example.dessin;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Cercle implements Parcelable {
    float cx, cy;
    float rayon;
    int couleur;

    public Cercle(float cx, float cy, float rayon, int couleur) {
        this.cx = cx;
        this.cy = cy;
        this.rayon = rayon;
        this.couleur = couleur;
    }

    protected Cercle(Parcel in) {
        cx = in.readFloat();
        cy = in.readFloat();
        rayon = in.readFloat();
        couleur = in.readInt();
    }

    public static final Creator<Cercle> CREATOR = new Creator<Cercle>() {
        @Override
        public Cercle createFromParcel(Parcel in) {
            return new Cercle(in);
        }

        @Override
        public Cercle[] newArray(int size) {
            return new Cercle[size];
        }
    };

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getRayon() {
        return rayon;
    }

    public int getCouleur() {
        return couleur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeFloat(cx);
        parcel.writeFloat(cy);
        parcel.writeFloat(rayon);
        parcel.writeInt(couleur);
    }
}
