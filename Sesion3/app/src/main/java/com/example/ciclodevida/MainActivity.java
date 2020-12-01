package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {

    //Variable de tipo MediaPlayer
    MediaPlayer mp;

    //La activity es creada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creacion de objeto de sonido
        mp = MediaPlayer.create(this, R.raw.zelda);
        //Inicio de la reproduccion de sonido
        mp.start();

        //Mensaje por corto tiempo del metodo usado
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    //Metodo usado cuando la activity inicia
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    //Metodo usado cuando la activity se reinicia
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        mp.start();
    }

    //La activuty retoma su ejecucion
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    //Metodo cuando dejas de usar la aplicacion, sales sin cerrar
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        //Pausa la cancion cuando la aplicacion esta en segundo plano
        mp.pause();
    }

    //Detiene el activity porque está en segundo plano
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        //mp.stop();
    }

    //La activity ya no será usada, se elimina
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    public void clic(View view) {
        //Intent para cambiar de activity
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        //Iniciar nueva activity
        startActivity(intent);
        //Aplicar animacion para la entrada de Activity 2
        Animatoo.animateSpin(this);
    }
}