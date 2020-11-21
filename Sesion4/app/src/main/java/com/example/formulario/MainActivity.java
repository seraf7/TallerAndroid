package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.formulario.models.Alumno;

public class MainActivity extends AppCompatActivity {

    int a;
    String saludo;

    Alumno alumno;  //Variable tipo Alumno
    EditText etNombre, etCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = 50;
        saludo = "Hola Sera";

        //Ligar objetos EditText
        etNombre = findViewById(R.id.etNombre);
        etCuenta = findViewById(R.id.etCta);
    }

    public void clic(View view) {

        //Validacion de los campos de texto
        if(validar()){
            //Instancia del objeto alumno
            alumno = new Alumno(etNombre.getText().toString(), etCuenta.getText().toString());

            //Variable para indicar la siguiente activity a ejecutar
            Intent intent = new Intent(this, MainActivity2.class);

            //Elemento para enviar datos entre activitys
            Bundle bundle = new Bundle();
            //Almacenar entero al bundle
            bundle.putInt("a", a);
            //Almacenar un String en el bundle
            bundle.putString("saludo", saludo);
            //Almacenar objeto alumno en el bundle
            bundle.putSerializable("alumno", alumno);

            //Enviar bundle al intent
            intent.putExtras(bundle);

            //Inicio de la Activity 2
            startActivity(intent);
        }
    }

    //Metodo para validar contenido de los campos de texto
    public boolean validar(){
        //Validar si el campo de nombre esta vacio
        if(etNombre.getText().toString().equals("")){
            //Mostrar mensaje de error
            etNombre.setError(getResources().getString(R.string.errorNomb));
            return false;
        }

        //Validar si el campo de cuenta esta vacio o tiene menos de 10 digitos
        if(etCuenta.getText().toString().equals("") || etCuenta.getText().toString().length() < 10){
            //Mostrar mensaje de error en el campo
            etCuenta.setError(getResources().getString(R.string.errorCta));
            return false;
        }

        return true;
    }
}