package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginmain.Model.Persona;
import com.google.android.gms.actions.ItemListIntents;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class Actualizar extends AppCompatActivity {
    private EditText Id,Nom,Ced, Cel;
    private TextView ID;
    private List<Persona> itemdetail = new ArrayList<>();
    private ItemListIntents it;
    private Button b;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        //referenciar
        b = (Button) findViewById(R.id.btnBuscar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mdata = firebaseDatabase.getReference();

        ID = (TextView) findViewById(R.id.txvID);
        Nom = (EditText) findViewById(R.id.actname);
        Ced = (EditText) findViewById(R.id.actcc);
        Cel = (EditText) findViewById(R.id.actCelular);
        Bundle miBundle = this.getIntent().getExtras();
        if (miBundle!=null){
            String nombre = miBundle.getString("itemView");
            ID.setText("Codigo: "+ nombre);
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType") Query q =mdata.orderByChild(getString(R.id.txvID)).equalTo(String.valueOf(ID));
            }
        });

    }

}