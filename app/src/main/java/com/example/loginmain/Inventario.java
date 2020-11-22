package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.loginmain.Model.Persona;
import com.example.loginmain.Model.myadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Inventario extends AppCompatActivity {

    RecyclerView reciview;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        reciview = (RecyclerView) findViewById(R.id.recview);
        reciview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Persona> options =
                new FirebaseRecyclerOptions.Builder<Persona>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reportes"), Persona.class)
                        .build();

        adapter = new myadapter(options);
        reciview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instanciar menu creado y realizacion de buscador
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView  = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                busqueda(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                busqueda(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void busqueda(String s) {
        FirebaseRecyclerOptions<Persona> options =
                new FirebaseRecyclerOptions.Builder<Persona>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reportes")
                                .orderByChild("Correo").startAt(s).endAt(s+"\uf8ff"), Persona.class)
                        .build();

        adapter = new myadapter(options);
        adapter.startListening();
        reciview.setAdapter(adapter);
    }
}