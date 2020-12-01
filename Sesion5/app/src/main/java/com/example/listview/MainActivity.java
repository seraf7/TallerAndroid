package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview.model.Anime;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Anime> datos = new ArrayList<>();
    Anime tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ligar List View
        lv = findViewById(R.id.lv);

        //Creacion de varios objetos anime
        for(int i = 1; i<10; i++) {
            //Creacion de un objeto Anime
            tmp = new Anime(i-1, getResources().getString(R.string.titulo)+i, getResources().getString(R.string.tipo)+i, getResources().getString(R.string.fecha)+i);
            //Agregar dato
            datos.add(tmp);
        }

        //Generacion del adaptador, contexto y conjunto de datos
        Adaptador adaptador = new Adaptador(this, datos);
        //Agrega elementos usando el list view
        lv.setAdapter(adaptador);

        //Metodo para reconocer clic en elementos
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Mostrar mensaje Toast con id del elemento
                //Toast.makeText(MainActivity.this, getResources().getString(R.string.clic)+id, Toast.LENGTH_LONG).show();

                //Obtener el elemento donde se hizo clic
                Anime anime = (Anime) parent.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this, getResources().getString(R.string.clicTipo)+anime.getTipo(), Toast.LENGTH_LONG).show();
            }
        });
    }
}