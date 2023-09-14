package com.example.convertisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    double conversion = 1;
    private static final double [] TABLE_CONVERSION = {0.86, 1.07, 0.95};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editTextNumber);
        Button euro_livre = findViewById(R.id.button);
        Button livre_euro = findViewById(R.id.button2);
        TextView tv = findViewById(R.id.textView);
        Spinner spin = findViewById(R.id.spinner);
        euro_livre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Euro Convert !", Toast.LENGTH_SHORT).show();
                double amount = Float.valueOf(editText.getText().toString());
                amount = amount * conversion;
                TextView tv2 = (TextView)spin.getSelectedView();
                tv.setText(String.valueOf(amount) + tv2.getText());
            }
        });
        livre_euro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Convert Euro !", Toast.LENGTH_SHORT).show();
                double amount = Float.valueOf(editText.getText().toString());
                amount = amount / conversion;
                TextView tv2 = (TextView)spin.getSelectedView();
                tv.setText(String.valueOf(amount) + "€");
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView)view;
                euro_livre.setText("€→"+tv.getText());
                livre_euro.setText(tv.getText()+"→€");
                conversion = TABLE_CONVERSION[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                conversion = 1;
            }
        };
        spinner.setOnItemSelectedListener(onItemSelectedListener);
    }
}