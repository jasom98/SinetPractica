package com.example.loginmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ContraOlvidada extends AppCompatActivity {

    private EditText email;
    private Button btnrestart;

    private String correo ="";

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contra_olvidada);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.TextEmail);
        btnrestart = (Button) findViewById(R.id.btnRestart);

        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = email.getText().toString();
                if (!correo.isEmpty()){
                    mDialog.setMessage("Un momento.....");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    ResetPassword();
                }else{
                    Toast.makeText(ContraOlvidada.this, "debe ingresar el correo", Toast.LENGTH_LONG).show();
                }
                mDialog.dismiss();
            }
        });



    }

    protected  void ResetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ContraOlvidada.this, "se envio un correo para restablecer tu contraseña", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ContraOlvidada.this, "No se logro enviar el correo de restablecimiento", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}