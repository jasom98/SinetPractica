package com.example.loginmain.Model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginmain.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class myadapter extends FirebaseRecyclerAdapter<Persona,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<Persona> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final Persona model) {
        holder.textViewNombre.setText(model.getNombre());
        holder.textViewCorreo.setText(model.getCorreo());
        holder.textViewCedula.setText(model.getCedula());
        holder.textViewCel.setText(model.getCelular());
        holder.textViewLugar.setText(model.getLugar());
        holder.textViewActividad.setText(model.getActividad());



        //parte de actualizar datos
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.edit.getContext())
                            .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                            .setExpanded(true,1300)
                            .create();

                    View myview = dialogPlus.getHolderView();

                    final EditText nom = myview.findViewById(R.id.actname);
                    final EditText corre = myview.findViewById(R.id.actemail);
                    final EditText ced = myview.findViewById(R.id.actcc);
                    final EditText cel = myview.findViewById(R.id.updateCelular);
                    final EditText Lug = myview.findViewById(R.id.updateLugar);
                    final EditText Act = myview.findViewById(R.id.updateActividad);

                    Button submit = myview.findViewById(R.id.btnBuscar);

                    nom.setText(model.getNombre());
                    corre.setText(model.getCorreo());
                    ced.setText(model.getCedula());
                    cel.setText(model.getCelular());
                    Lug.setText(model.getCelular());
                    Act.setText(model.getActividad());

                    dialogPlus.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("Nombre",nom.getText().toString());
                            map.put("Correo",corre.getText().toString());
                            map.put("Cedula",ced.getText().toString());
                            map.put("Celular",cel.getText().toString());
                            map.put("Lugar", Lug.getText().toString());
                            map.put("Actividad",Act.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("Reportes")
                                    .child(getRef(position).getKey())
                            .updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dialogPlus.dismiss();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialogPlus.dismiss();
                                        }
                                    });

                        }
                    });
                }
            });

            //borrardatos
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.delete.getContext());
                    builder.setTitle("Ventana de Borrado");
                    builder.setMessage("Seguro quieres borrar el registro?");

                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            FirebaseDatabase.getInstance().getReference().child("Reportes")
                                    .child(getRef(position).getKey())
                                    .removeValue();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_registros, parent,false);
        return new myviewholder(v);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView textViewNombre,textViewCorreo,textViewCedula,textViewCel,textViewLugar,textViewActividad;
        ImageView edit,delete;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            textViewNombre =  (TextView) itemView.findViewById(R.id.nombreRep);
            textViewCorreo = (TextView) itemView.findViewById(R.id.correoRep);
            textViewCedula = (TextView) itemView.findViewById(R.id.cedRep);
            textViewCel = (TextView) itemView.findViewById(R.id.celRep);
            textViewLugar = (TextView) itemView.findViewById(R.id.lugRep);
            textViewActividad = (TextView) itemView.findViewById(R.id.ActRep);

            edit = (ImageView) itemView.findViewById(R.id.editicon);
            delete = (ImageView) itemView.findViewById(R.id.deleteicon);
        }
    }

}
