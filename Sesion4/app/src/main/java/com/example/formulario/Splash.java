package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Creacion de un Splash Screen, mostrar un pantalla y cambiarla automaticamente

        //Instancia de un hilo para realizar el cambio de activity
        Thread timer = new Thread(){
            public void run(){
                try{
                    //Mantiene Activity Splash 1.5s
                    sleep(1500);
                }catch (Exception e){

                }finally{
                    //Para realizar el cambio de de activity
                    //Se usa la activity actual y la clase de la Activity siguiente
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    //Iniciar la nueva Activity indicada
                    startActivity(intent);
                    //Terminar la Activity anterior y sacarlo de memoria
                    finish();
                }
            }
        };

        //Ejecucion del hilo definido
        timer.start();
    }
}