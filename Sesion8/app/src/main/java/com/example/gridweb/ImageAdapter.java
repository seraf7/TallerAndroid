package com.example.gridweb;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.gridweb.model.Imagen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    Context context;
    int ancho;
    int columnas = 3;

    ArrayList<Imagen> imagenes;

    public ImageAdapter(Context context, int ancho, ArrayList<Imagen> imagenes) {
        this.context = context;
        this.ancho = ancho;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        //Retorna el tamaño del ArrayList
        return imagenes.size();
    }

    @Override
    public Object getItem(int position) {
        //Retorna el elemento de la posicion seleccionada
        return imagenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Retorna el ID del elemento seleccionado
        return imagenes.get(position).getId();
    }

    //Carga una imagen por cada elemento del arreglo
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Instancia de ImageView en el Grid
        ImageView imageView = new ImageView(context);
        //Establcer tamaño del ImageView
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ancho/columnas-20, ancho/columnas-20));
        //Establecer un margen
        imageView.setPadding(15, 15, 15, 15);

        //Creacion del URL de la imagen
        String url = context.getResources().getString(R.string.url_base)+imagenes.get(position).getUrl_img();
        //Log.d("URL", url);

        //Carga la imagen en la vista usando URL donde se encuentra
        Picasso.with(context).load(url).into(imageView);

        return imageView;
    }
}
