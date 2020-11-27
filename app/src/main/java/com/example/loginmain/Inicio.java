package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
    //public static String user;
    private Button irReport;
    private Button irAct;
    private Button inv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inicio);

        //reportes
        irReport = findViewById(R.id.btnreport);
        irReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Reporte.class);
                startActivity(in);
            }
        });
        //Actividades
        irAct = findViewById(R.id.btnMir);
        irAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(getApplicationContext(), ActividadesRealizadas.class);
                startActivity(in2);
            }
        });
        //Inventario
        /*inv = findViewById(R.id.btninv);
        inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3 = new Intent(getApplicationContext(), Inventario.class);
                startActivity(in3);
            }
        });*/
    }
}