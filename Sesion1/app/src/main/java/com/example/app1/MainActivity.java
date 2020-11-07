package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "INFORMACION";
    TextView tvSaludo;
    Button btnSaludar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Asocia una vista al activity
        setContentView(R.layout.activity_main);

        int a = 5;
        int b = 10;

        Log.i(LOGTAG, "Hola mundo!");

        Log.i(LOGTAG, "El valor de a es: " + a);
        Log.i(LOGTAG, "El valor de b es: " + b);
        Log.i(LOGTAG, "El valor de a + b es: " + (a+b));

        //Ligar objeto mediante ID
        tvSaludo = findViewById(R.id.tvSaludo);
        btnSaludar = findViewById(R.id.btnSaludo);

        //Modificar texto del textView
        //tvSaludo.setText("Computo Móvil");
        //tvSaludo.setText(getResources().getString(R.string.app_name));

        //Asociar un evento al boton
        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSaludo.setText("Hola Sera");
            }
        });

        tvSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSaludo.setText("¡Hola Mundo!");
            }
        });
    }
}