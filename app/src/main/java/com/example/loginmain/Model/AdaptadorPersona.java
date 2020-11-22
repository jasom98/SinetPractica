package com.example.loginmain.Model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginmain.Actualizar;
import com.example.loginmain.R;

import java.util.List;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.RegistrosViewHolder>
implements View.OnClickListener{

    List<Persona> RegAct;
    private  View.OnClickListener listener;

    public AdaptadorPersona(List<Persona> regAct) {
        this.RegAct = regAct;
    }

    @NonNull
    @Override
    public RegistrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_registros, parent,false);
        RegistrosViewHolder holder = new RegistrosViewHolder(v);
        v.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RegistrosViewHolder holder, int position) {
        final Persona reg = RegAct.get(position);
        //holder.textViewId.setText("ID: "+reg.getID());
        holder.textViewNombre.setText("Nombre Operador:\n"+reg.getNombre());
        holder.textViewCorreo.setText("Correo:\n"+reg.getCorreo());
        holder.textViewCedula.setText("Cedula:\n"+reg.getCedula());
        holder.textViewCel.setText("Celular:\n"+reg.getCelular());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), Actualizar.class);
                in.putExtra("itemView",reg.getID());
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return RegAct.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!= null){
            listener.onClick(v);
        }
    }

    public class RegistrosViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNombre,textViewCorreo,textViewCedula,textViewCel,textViewId;

        public RegistrosViewHolder(View itemView){
            super(itemView);
           // textViewId =(TextView) itemView.findViewById(R.id.IdRep);
            textViewNombre =  (TextView) itemView.findViewById(R.id.nombreRep);
            textViewCorreo = (TextView) itemView.findViewById(R.id.correoRep);
            textViewCedula = (TextView) itemView.findViewById(R.id.cedRep);
            textViewCel = (TextView) itemView.findViewById(R.id.celRep);
        }
    }
}
