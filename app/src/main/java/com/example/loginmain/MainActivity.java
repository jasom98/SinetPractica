package com.example.loginmain;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaracion de objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    //Referenciar objetos
    private Button btnlogin;
    private EditText TextEmail;
    private EditText TextContraseña;
    private Button reset;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar el objeto de firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //Inicializar referencias
        TextEmail = (EditText) findViewById(R.id.txtcorreo);
        TextContraseña = (EditText) findViewById(R.id.txtcontraseña);
        reset = (Button) findViewById(R.id.btnRestablecer);


        //boton de ayuda
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Help Button :'(", Toast.LENGTH_LONG).show();
            }
        });

        //boton login
        btnlogin = findViewById(R.id.btnlogin);
       btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (validar()){
                    //Intent nw = new Intent(getApplication(), Inicio.class);
                    //startActivity(nw);
                    LoginUsuario();
                    TextContraseña.setText("");

                }
                //LoginUsuario();
                //TextEmail.setText("");
                //TextContraseña.setText("");
            }
        });

       reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent contra = new Intent(getApplication(), ContraOlvidada.class);
               startActivity(contra);
           }
       });
    }

    public void mensaje_ini(){
        Toast.makeText(getApplicationContext(), "Ingresaste al sistema", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           //int id = item.getItemId();

           //if (id == R.id.action_settings) {
            //return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    private void LoginUsuario(){
        final String correo = TextEmail.getText().toString().trim();
        String contra = TextContraseña.getText().toString().trim();

        if (TextUtils.isEmpty(correo)){
            Toast.makeText(this, "Ingrese un correo", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(contra)){
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        //progressDialog.setMessage("Consultando Usuario.....");
        //progressDialog.show();

        //loguear con email y password
        firebaseAuth.signInWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            int pos = correo.indexOf("@");
                            String user = correo.substring(0,pos);
                            Toast.makeText(MainActivity.this, "Bienvenido",
                                    Toast.LENGTH_LONG).show();
                            Intent ya = new Intent(getApplication(), Inicio.class);
                            //ya.putExtra(Inicio.user, user);
                            startActivity(ya);
                            //TextEmail.setText("");
                            //TextContraseña.setText("");
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(MainActivity.this, "Usuario ya registrado",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Usuario no encontrado",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        //progressDialog.dismiss();
                    }
                });
    }

    public boolean validar(){

        boolean retorno=true;

        String c1= TextEmail.getText().toString();
        String c2= TextContraseña.getText().toString();

        if (c1.isEmpty()){
            TextEmail.setError("Este Campo no puede quedar vacio");
            retorno=false;

        }
        if (c2.isEmpty()) {
            retorno = false;
            TextContraseña.setError("Este Campo no puede quedar vacio");
        }
        return retorno;
    }

    private void RestablecerContra(){

    }
    
}