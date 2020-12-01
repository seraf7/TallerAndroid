package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listview.model.Anime;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    Context contexto;
    ArrayList<Anime> datos;     //Arreglo de objetos anime
    //Variable estatica de tipo inflater
    private static LayoutInflater inflater = null;

    //Constructor de la clase
    public Adaptador(Context contexto, ArrayList<Anime> datos){
        this.contexto = contexto;
        this.datos = datos;
        //Objeto para agregar elementos a la vista
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { //Indica el numero de elementos a mostrar
        //Decuelve numero de datos en el Array
        return datos.size();
    }

    @Override
    public Object getItem(int position) {   //Permite recuperar un elemento particular
        //Devuelve item de una posicion
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {   //Obtiene id de un elemento
        //Devuelve ID de un elemento
        return datos.get(position).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) { //Genera las vistas de cada celda prototipo
        //Objeto que contiene la vista
        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        //Ligar elementos de la vista
        TextView tvTitulo = vista.findViewById(R.id.tvTitutlo);
        TextView tvTipo = vista.findViewById(R.id.tvTipo);
        TextView tvFecha = vista.findViewById(R.id.tvFecha);

        //Modificar datos de la vista
        tvTitulo.setText(datos.get(i).getTitulo());
        tvTipo.setText(datos.get(i).getTipo());
        tvFecha.setText(datos.get(i).getFecha());

        return vista;
    }
}
