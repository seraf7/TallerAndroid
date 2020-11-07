package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    TextView tvSaludo;
    Button btnSaludar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvSaludo = findViewById(R.id.tvSaludo);
        btnSaludar = findViewById(R.id.btnSaludo);

        tvSaludo.setOnClickListener(this);
        btnSaludar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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