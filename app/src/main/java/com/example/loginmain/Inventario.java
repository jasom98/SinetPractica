package com.example.loginmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
}