package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.formulario.models.Alumno;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String saludo;
        int a;
        Alumno alumno;

        //Para recuperar datos de Activity anterior
        Bundle bundle = getIntent().getExtras();

        //Verificar que el bundle no está vacio
        if(bundle != null){
            //Recuperar elemento a traves de su llave, uso de valor Default
            saludo = bundle.getString("saludo", "");
            a = bundle.getInt("a", 0);

            //Instanciar alumno a partir del objeto almacenado en el bundle
            alumno = (Alumno) bundle.getSerializable("alumno");

            //Mensaje Toast para mostrar valores recibidos
            //Toast.makeText(this, "El saludo es: " + saludo + "; y el numero es: " + a, Toast.LENGTH_LONG).show();

            //Mostrar atributos del alumno recibido
            Toast.makeText(this, getResources().getString(R.string.nomb) + alumno.getNombre() + getResources().getString(R.string.cta) + alumno.getCuenta(), Toast.LENGTH_LONG).show();
        }
    }
}