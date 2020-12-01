package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Agregar una transicion aplicado a entrada y posicion final
        //overridePendingTransition(R.anim.in, R.anim.hold);

        //Agragar animacion de Animatoo

        setContentView(R.layout.activity_main2);
    }

    public void clic(View view) {
        //Intent para cambiar de activity
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        //Iniciar nueva activity
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Aplica una transicion de salida
        //overridePendingTransition(R.anim.hold, R.anim.out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Aplicar animacion al presionar boton atras
        Animatoo.animateShrink(this);
    }
}