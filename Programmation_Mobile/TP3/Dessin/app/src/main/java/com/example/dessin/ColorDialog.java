package com.example.dessin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ColorDialog extends DialogFragment {

    Dessin dessin;

    public ColorDialog(Dessin dessin) {
        this.dessin = dessin;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Color");
        CharSequence[] colors = {"Black", "Red", "Blue", "Green"};
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        dessin.setCouleur(Color.BLACK);
                        break;
                    case 1:
                        dessin.setCouleur(Color.RED);
                        break;
                    case 2:
                        dessin.setCouleur(Color.BLUE);
                        break;
                    case 3:
                        dessin.setCouleur(Color.GREEN);
                        break;
                }
            }
        });
        return builder.create();
    }
}
