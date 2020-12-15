package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    int ancho, alto;

    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtener caracteristicas de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        //Obtener el tamaño de la pantalla
        display.getSize(size);

        //Gardar ancho y alto de la pantalla
        ancho = size.x;
        alto = size.y;

        //Asociar GriedView
        gv = findViewById(R.id.gv);
        //Establecer el numero de columnas del GriedView
        gv.setNumColumns(3);

        //Instancia del ImageAdapter con informacion de la pantalla
        ImageAdapter adapter = new ImageAdapter(this, ancho);

        //Asociar adaptador con el GridView
        gv.setAdapter(adapter);

        //Agregar un listener al GriedView
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent para indicar siguiente Antivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                //Creacion de bundle para envio de parametros
                Bundle bundle = new Bundle();
                //Guardar ID del elemento seleccionado
                bundle.putLong("id", id);
                intent.putExtras(bundle);

                //Inicio de la segunda Activity
                startActivity(intent);
            }
        });
    }
}