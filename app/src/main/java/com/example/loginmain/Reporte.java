package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginmain.Model.Persona;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Reporte extends AppCompatActivity {


    private EditText txtNombresRep;
    private EditText txtCorreoRep;
    private EditText txtCedulaRep;
    private EditText txtCelularRep;
    private EditText txtLugarRep;
    private EditText txvAct;
    private Button btnsaves;
    private RadioButton rdCam,rdTv,rdInter;

    //referencias a base de datos
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        //intanciacion base de datos
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //intanciacion objetos
        txtNombresRep = (EditText) findViewById(R.id.txtname);
        txtCorreoRep = (EditText) findViewById(R.id.txtemail);
        txtCedulaRep = (EditText) findViewById(R.id.txtcc);
        txtCelularRep = (EditText) findViewById(R.id.txtCelular);
        txtLugarRep = (EditText) findViewById(R.id.txtLugar);

        txvAct = (EditText) findViewById(R.id.txvActividad);

        rdCam = (RadioButton) findViewById(R.id.rdCamaras);
        rdTv = (RadioButton) findViewById(R.id.rdTV);
        rdInter = (RadioButton) findViewById(R.id.rdInternet);


        btnsaves = findViewById(R.id.btnsavereport);
        btnsaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validar()){
                    Subirregistros();
                    Intent in = new Intent(getApplication(),Inicio.class);
                    startActivity(in);
                }
            }
        });
        RadioSeleccion();
    }

    public boolean Validar(){

        boolean retorno = true;


        String c2 = txtCelularRep.getText().toString();
        String c3 = txtCedulaRep.getText().toString();
        String c4 = txtNombresRep.getText().toString();
        String c5 = txtCorreoRep.getText().toString();



        if (c2.isEmpty()) {
            txtCelularRep.setError("Este Campo no puede quedar vacio");
            retorno = false;
        }

        if (c3.isEmpty()) {
            txtCedulaRep.setError("Este Campo no puede quedar vacio");
            retorno = false;
        }

        if (c4.isEmpty()) {
            txtNombresRep.setError("Este Campo no puede quedar vacio");
            retorno = false;
        }

        if (c5.isEmpty()) {
            txtCorreoRep.setError("Este Campo no puede quedar vacio");
            retorno = false;
        }

        return retorno;
    }

    private void RadioSeleccion(){
        rdInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvAct.setText("Instalación de internet");
            }
        });

        rdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvAct.setText("Instalación de TV");
            }
        });

        rdCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvAct.setText("Instalación de Camaras");
            }
        });

    }

    public void Subirregistros(){

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String ID = UUID.randomUUID().toString();
        String nombre = txtNombresRep.getText().toString();
        String correo = txtCorreoRep.getText().toString();
        String cedula = txtCedulaRep.getText().toString();
        String celular = txtCelularRep.getText().toString();
        String Lugar = txtLugarRep.getText().toString();
        String  act = txvAct.getText().toString();


        Map<String,Object> registroAct = new HashMap<>();


        //registroAct.put("ID",ID);
        registroAct.put("Fecha",date);
        registroAct.put("Nombre", nombre);
        registroAct.put("Correo", correo);
        registroAct.put("Cedula", cedula);
        registroAct.put("Celular", celular);
        registroAct.put("Lugar",Lugar);
        registroAct.put("actividad", act);


        mDatabase.child("Reportes").child(ID).setValue(registroAct);


    }
}