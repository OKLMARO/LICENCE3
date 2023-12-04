package com.example.tp5a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int red = 0;

    int green = 0;

    int blue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        button1.setText(red + "");
        button2.setText(green + "");
        button3.setText(blue + "");
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.GREEN);
        button3.setBackgroundColor(Color.BLUE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red = red + 32;
                if(red == 256){
                    red = 0;
                }
                button1.setText(red + "");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                green = green + 32;
                if(green == 256){
                    green = 0;
                }
                button2.setText(green + "");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue = blue + 32;
                if(blue == 256){
                    blue = 0;
                }
                button3.setText(blue + "");
            }
        });

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(Math.sqrt(event.values[0] + event.values[1] + event.values[2]) > 0.7 || Math.sqrt(event.values[0] + event.values[1] + event.values[2]) < 1.3){

                    Color.rgb(red, green, blue);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }


}