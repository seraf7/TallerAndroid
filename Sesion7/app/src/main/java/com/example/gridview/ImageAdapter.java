package com.example.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    Context context;
    int ancho;

    int columnas = 3;

    //Arreglo para guardar ID's de las imagenes
    private Integer[] thumbs = {
        R.drawable.imag1,
        R.drawable.imag2,
        R.drawable.imag3,
        R.drawable.imag4,
        R.drawable.imag5,
        R.drawable.imag6,
        R.drawable.imag7,
        R.drawable.imag8,
        R.drawable.imag9,
        R.drawable.imag10,
        R.drawable.imag1,
        R.drawable.imag2,
        R.drawable.imag3,
        R.drawable.imag4,
        R.drawable.imag5,
        R.drawable.imag6,
        R.drawable.imag7,
        R.drawable.imag8,
        R.drawable.imag9,
        R.drawable.imag10
    };

    //Elemento para redimensionar
    CargaImagen cargaImagen;

    public ImageAdapter(Context context, int ancho) {
        this.context = context;
        this.ancho = ancho;
        this.cargaImagen = new CargaImagen();
    }

    @Override
    public int getCount() {
        //Devuelve el tamaño del arreglo de enteros (imagenes)
        return thumbs.length;
    }

    @Override
    public Object getItem(int position) {
        //Devuelve el item seleccionado
        return thumbs[position];
    }

    @Override
    public long getItemId(int position) {
        //Devuelve el ID del item seleccionado
        return thumbs[position];
    }

    //Se ejecuta el mismo numero de veces que el tamaño del arreglo, rellena la vista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Instancia de una ImageView en la vista
        ImageView imageView = new ImageView(context);
        //Asignar tamaño del contenedor del ImageView
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ancho/columnas-20, ancho/columnas-20));
        //Agregar Padding (margen) a ImageView
        imageView.setPadding(15, 15, 15, 15);

        //Asignar la imagen que debe verse en el ImageView
        //imageView.setImageResource(thumbs[position]);

        //Cargar imagen redimensionada de la imagen
        imageView.setImageBitmap(
                cargaImagen.decodeSampledBitmapFromResource(context.getResources(), thumbs[position], 100, 100));

        return imageView;
    }
}
