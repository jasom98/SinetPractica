package com.example.loginmain.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginmain.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<Persona,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<Persona> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Persona model) {
        holder.textViewNombre.setText("Nombre Operador:\n"+model.getNombre());
        holder.textViewCorreo.setText("Correo:\n"+model.getCorreo());
        holder.textViewCedula.setText("Cedula:\n"+model.getCedula());
        holder.textViewCel.setText("Celular:\n"+model.getCelular());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_registros, parent,false);
        return new myviewholder(v);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView textViewNombre,textViewCorreo,textViewCedula,textViewCel,textViewId;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            textViewNombre =  (TextView) itemView.findViewById(R.id.nombreRep);
            textViewCorreo = (TextView) itemView.findViewById(R.id.correoRep);
            textViewCedula = (TextView) itemView.findViewById(R.id.cedRep);
            textViewCel = (TextView) itemView.findViewById(R.id.celRep);
        }
    }

}
