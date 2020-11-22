package com.example.loginmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.loginmain.Model.AdaptadorPersona;
import com.example.loginmain.Model.Persona;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Actividades extends AppCompatActivity {

    RecyclerView rv;

    List<Persona> RepPersona;
    AdaptadorPersona adapter;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    Persona personaSelect;

    private Button btnActu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        database = FirebaseDatabase.getInstance();
        inicializarFirebase();

        //botones


        //inicializo el recycler view
        rv = (RecyclerView) findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        RepPersona = new ArrayList<>();
        adapter = new AdaptadorPersona(RepPersona);
        rv.setAdapter(adapter);

        ListarRegistros();
        ActualizarRegistros();
    }

    private void ActualizarRegistros(){


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nombre: " + RepPersona.get(rv.getChildAdapterPosition(v))
                        .getID(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ListarRegistros(){
        database.getReference().getRoot().child("Reportes").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RepPersona.removeAll(RepPersona);
                        for (DataSnapshot snap : snapshot.getChildren()){
                            Persona p = snap.getValue(Persona.class);
                            RepPersona.add(p);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }



    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        //database.setPersistenceEnabled(true);
        databaseReference = database.getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividades,menu);

        //implementacion de buscador
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
               processearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processearch(String s) {
        FirebaseRecyclerOptions<Persona> options =
                new FirebaseRecyclerOptions.Builder<Persona>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reportes"), Persona.class)
                        .build();

        //adapter = new AdaptadorPersona(options);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //swicth
        switch (item.getItemId()){
            case R.id.icon_add:{
                Toast.makeText(this, "Agregar",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete:{
                Toast.makeText(this, "Borrar",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_save:{
                Toast.makeText(this, "Guardar", Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }

        return true;
    }


}