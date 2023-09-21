package com.example.tp2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    Button buttonClick;

    Button buttonFilter;

    Bitmap bitmap;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        buttonClick = findViewById(R.id.button);
        buttonFilter = findViewById(R.id.button2);
        spinner = findViewById(R.id.spinner);

        buttonClick.setOnClickListener(v -> {
            selectPicture.launch(null);
        });

        buttonFilter.setOnClickListener(v -> {
            switch ((int) spinner.getSelectedItemId()){
                case 0:
                    permute(bitmap);
                    imageView.setImageBitmap(bitmap);
                    break;
                case 1:
                    reverse(bitmap);
                    imageView.setImageBitmap(bitmap);
                    break;
            }
        });

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View parent, ViewGroup viewGroup) {
                TextView view = new TextView(getBaseContext());
                view.setTextSize(30);
                switch(i){
                    case 0:
                        view.setText("Permutation");
                        break;
                    case 1 :
                        view.setText("Renverser");
                        break;
                }
                return view;
            }
        };

        spinner.setAdapter(adapter);
    }

    private ActivityResultCallback<Bitmap> callback = new ActivityResultCallback<Bitmap>() {
        @Override
        public void onActivityResult(Bitmap result) {
            bitmap = result.copy(Bitmap.Config.ARGB_8888,true);
            imageView.setImageBitmap(bitmap);
        }
    };

    private ActivityResultContract<Void, Bitmap> contract = new ActivityResultContract<Void, Bitmap>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Void unused) {
            return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        }

        @Override
        public Bitmap parseResult(int resultCode, @Nullable Intent result) {
            return (Bitmap) result.getExtras().get("data");
        }
    };
    ActivityResultLauncher<Void> selectPicture = registerForActivityResult(contract,callback);

    private void permute(Bitmap bitmap){
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        for (int x = 0; x < bitmapWidth; x++) {
            for (int y = 0; y < bitmapHeight; y++) {
                int colorPixel = bitmap.getPixel(x, y);
                int red = Color.red(colorPixel);
                int green = Color.green(colorPixel);
                int blue = Color.blue(colorPixel);
                bitmap.setPixel(x, y, Color.rgb(blue, red, green));
            }
        }
    }

    private void reverse(Bitmap bitmap){
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        for (int x = 0; x < bitmapWidth; x++) {
            for (int y = 0; y < bitmapHeight; y++) {
                int colorPixel = bitmap.getPixel(x, y);
                int red = Color.red(colorPixel);
                int green = Color.green(colorPixel);
                int blue = Color.blue(colorPixel);
                bitmap.setPixel(x, y, Color.rgb(255 - red, 255 - green, 255 - blue));
            }
        }
    }
}