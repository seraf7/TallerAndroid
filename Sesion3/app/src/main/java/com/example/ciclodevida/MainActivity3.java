package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void clic(View view) {
        //Intent para cambiar de activity
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        //Limpiar pila de activities
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Iniciar nueva activity
        startActivity(intent);
    }
}