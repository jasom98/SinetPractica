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
        holder.textViewNombre.setText("Nombre Operador:\n"+model.getNombre());
        holder.textViewCorreo.setText("Correo:\n"+model.getCorreo());
        holder.textViewCedula.setText("Cedula:\n"+model.getCedula());
        holder.textViewCel.setText("Celular:\n"+model.getCelular());



        //parte de actualizar datos
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.edit.getContext())
                            .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                            .setExpanded(true,1100)
                            .create();

                    View myview = dialogPlus.getHolderView();

                    final EditText nom = myview.findViewById(R.id.actname);
                    final EditText corre = myview.findViewById(R.id.actemail);
                    final EditText ced = myview.findViewById(R.id.actcc);
                    final EditText cel = myview.findViewById(R.id.updateCelular);

                    Button submit = myview.findViewById(R.id.btnBuscar);

                    nom.setText(model.getNombre());
                    corre.setText(model.getCorreo());
                    ced.setText(model.getCedula());
                    cel.setText(model.getCelular());

                    dialogPlus.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("Nombre",nom.getText().toString());
                            map.put("Correo",corre.getText().toString());
                            map.put("Cedula",ced.getText().toString());
                            map.put("Celular",cel.getText().toString());

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

        TextView textViewNombre,textViewCorreo,textViewCedula,textViewCel,textViewId;
        ImageView edit,delete;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            textViewNombre =  (TextView) itemView.findViewById(R.id.nombreRep);
            textViewCorreo = (TextView) itemView.findViewById(R.id.correoRep);
            textViewCedula = (TextView) itemView.findViewById(R.id.cedRep);
            textViewCel = (TextView) itemView.findViewById(R.id.celRep);

            edit = (ImageView) itemView.findViewById(R.id.editicon);
            delete = (ImageView) itemView.findViewById(R.id.deleteicon);
        }
    }

}
