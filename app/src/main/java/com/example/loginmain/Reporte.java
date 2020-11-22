package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText txtDescripcionRep;
    private Button btnsaves;



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
        txtDescripcionRep = (EditText) findViewById(R.id.txtDesc);


        btnsaves = findViewById(R.id.btnsavereport);
        btnsaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subirregistros();
                Intent in = new Intent(getApplication(),Inicio.class);
                startActivity(in);

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


        Map<String,Object> registroAct = new HashMap<>();
        registroAct.put("ID",ID);
        registroAct.put("Fecha",date);
        registroAct.put("Nombre", nombre);
        registroAct.put("Correo", correo);
        registroAct.put("Cedula", cedula);
        registroAct.put("Celular", celular);

        mDatabase.child("Reportes").child(ID).setValue(registroAct);


    }
}