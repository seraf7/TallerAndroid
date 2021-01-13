package com.example.ireaderqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Intent para indicar siguiente Activity
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    //Inicia el nuevo Activity
                    startActivity(intent);
                    finish();
                }
            }
        };
        //Inicia el hilo del Splash
        timer.start();
    }
}