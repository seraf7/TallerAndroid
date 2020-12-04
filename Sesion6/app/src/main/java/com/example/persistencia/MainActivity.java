package com.example.persistencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Fragment navHostFragment;

    //Variables para guardar configuraciones de usuario
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Liga el activity con una vista determinada
        setContentView(R.layout.activity_main);
        //Liga Toolbar a la interfaz
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Ligar Frafment a la vista
        navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        //Instanciar preferencias de usuario, para que solo la aplicacion tenga acceso a ellas
        sp = getPreferences(Context.MODE_PRIVATE);
        //Permite editar las preferencias
        editor = sp.edit();

        //Recuperar datos de preferencias de usuario, el valor por primera vez es blanco
        int color = sp.getInt("color", R.color.white);
        cambiaColor(color);

        //Liga el boton flotante
        FloatingActionButton fab = findViewById(R.id.fab);
        //Método para reconocer clic en el boton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mensaje de tipo Snackbar
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        //Asociar accion a cada opcion del menu
        switch (id){
            case R.id.action_azul:
                cambiaColor(R.color.Azul);
                break;
            case  R.id.action_rojo:
                cambiaColor(R.color.Rojo);
                break;
            case R.id.action_verde:
                cambiaColor(R.color.Verde);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cambiaColor(int color){
        //Modifica color de fondo
        navHostFragment.getView().setBackgroundColor(getResources().getColor(color));
        //Llama metodo para guardar color
        guardaColor(color);
    }

    private void guardaColor(int color){
        //Guarda el valor del color asociado a una clave
        editor.putInt("color", color);
        editor.commit();
    }
}