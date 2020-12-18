package com.example.gridweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>{

    ImageView iv;
    ProgressBar pbConexion2;

    String url;
    RequestQueue queue;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Asociar elementos de la vista
        iv = findViewById(R.id.iv);
        pbConexion2 = findViewById(R.id.pbConexion2);

        //Recuperar informacion del obejto seleccionado en Activity anterior
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        int id = (int) bundle.getLong("id");

        //Generacion de un mapa de valores
        Map<String, String> params = new HashMap<String, String>();
        //Ingresar valores al mapa
        params.put("id", String.valueOf(id));

        //Creacion de cola de conexiones
        queue = Volley.newRequestQueue(this);
        //Definicion de la URL
        url = getResources().getString(R.string.url_base) + "peticion";

        //Generacion de la solicitud, se usa el objeto JSON a enviar como parametro
        request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), this, this){
            //Metodo para el envio de un JSON
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Generacion de un mapa de valores
                Map<String, String> headers = new HashMap<String, String>();
                //Establece cabeceras del mensaje
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        //Agregar request a la cola de conexiones
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //Quitar ProgressBarr al terminar conexion
        pbConexion2.setVisibility(View.GONE);
        Toast.makeText(this, getResources().getString(R.string.errorConexion), Toast.LENGTH_LONG).show();

        Log.d("Error", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        //Quitar ProgressBarr al terminar conexion
        pbConexion2.setVisibility(View.GONE);

        try {
            //Asigancion del objeto JSON de la respuesta del servidor
            JSONObject json = response;

            //Uso del titulo obtenido del objeto JSON
            Toast.makeText(this, json.getString("titulo"), Toast.LENGTH_SHORT).show();

            //Carga de la imagen con direccion del servidor
            Picasso.with(this).load(getResources().getString(R.string.url_base) + json.getString("url_img")).into(iv);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}