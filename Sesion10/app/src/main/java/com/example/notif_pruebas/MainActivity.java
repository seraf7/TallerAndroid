package com.example.notif_pruebas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Parametros a almacenar en el Analytics
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "01");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Pantalla principal");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Pantalla");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Añadir propiedades de usuario al Analytics
        mFirebaseAnalytics.setUserProperty("Comida_favorita", "Tacos");

        //Para obtener el token generador
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                //Valida si la tarea no fue exitosa
                if(!task.isSuccessful()){
                    //No se pudo obtener el token
                    return;
                }
                String token = task.getResult();
                Log.d("TOKEN_APP", "Token generado: " + token);
            }
        });

        //Bundle para recibir informacion de una notificacion
        Bundle bundle2 = new Bundle();
        bundle2 = getIntent().getExtras();

        if(bundle2 != null){
            String appPackage = bundle2.getString("appPackage", "");
            int irApp = Integer.valueOf(bundle2.getString("irApp", "0"));

            if(irApp == 1 && !appPackage.isEmpty()){
                //Abre la tienda con el URL de aplicacion indicado
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackage)));
                finish();
            }
        }
    }

    public void clic(View view) {
        //Evento personalizado para registrar en Analytics
        Bundle evento1 = new Bundle();
        evento1.putString("acción", "Clic");
        evento1.putString("texto_botón", getResources().getString(R.string.btnIngresar));
        mFirebaseAnalytics.logEvent("clic_botón", evento1);
    }
}