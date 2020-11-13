package com.example.esprimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNumero;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = findViewById(R.id.etNumero);
        tvResultado = findViewById(R.id.tvResultado);

        //Cambio de la fuente por un archivo externo
        etNumero.setTypeface(ResourcesCompat.getFont(this, R.font.bryndanwrite));
    }

    public void clic(View view) {
        //Revisar que hay texto en la caja
        if(!etNumero.getText().toString().equals("")){
            //Obtener valor numerico
            int numero = Integer.parseInt(etNumero.getText().toString());

            //Validar resultado de la funcion
            if(esPrimo(numero)){
                //Impresion de resultado en el text label
                tvResultado.setText(getResources().getString(R.string.resp_inicial)+numero+getResources().getString(R.string.si_primo));
            }else{
                tvResultado.setText(getResources().getString(R.string.resp_inicial)+numero+getResources().getString(R.string.no_primo));
            }
        }else{  //Usuario no ha ingresado nada
            //Muestra un mensaje flotante por cierto tiempo
            Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            //Muestra mensaje de error en la caja de texto
            etNumero.setError(getResources().getString(R.string.valore_requerido));
        }
    }

    //Metodo de verificacion del numero
    public boolean esPrimo(int n){
        if(n < 2) return false;
        else{
            for(int i = 2; i < n; i++){
                if(n % i == 0) return  false;
            }
        }
        return true;
    }
}