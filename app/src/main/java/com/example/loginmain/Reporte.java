package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Reporte extends AppCompatActivity {
    private Button btnsaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        btnsaves = findViewById(R.id.btnsavereport);
        btnsaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "a precionado el boton de guardar", Toast.LENGTH_LONG).show();
            }
        });
    }
}