package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView tvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Asociacion a un elemento mediante ID
        tvSaludo = findViewById(R.id.tvSaludo);
    }

    public void clics(View view) {
        switch(view.getId()){
            case R.id.tvSaludo:
                tvSaludo.setText("Hola Mundo");
                break;
            case R.id.btnSaludo:
                tvSaludo.setText("Hola Sera");
                break;
            default:
                break;
        }
    }
}