package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    ImageView imageView;
    CargaImagen cargaImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Asociar ImageView
        imageView = findViewById(R.id.imageView);

        //Recuperar ID del elemento seleccionado
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        int id = (int) bundle.getLong("id");

        //Inatancia de objeto para cargar imagenes
        cargaImagen = new CargaImagen();

        //Cargar imagen redimensionada
        imageView.setImageBitmap(cargaImagen.decodeSampledBitmapFromResource(getResources(), id, 300, 300));
    }
}