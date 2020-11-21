package com.example.loginmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Actividades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividades,menu);
        return super.onCreateOptionsMenu(menu);
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
                Toast.makeText(this, "Guardar",Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }

        return true;
    }
}