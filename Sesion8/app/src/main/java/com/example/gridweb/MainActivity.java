package com.example.gridweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gridweb.model.Imagen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//Se indica que se recibiran arrays de objetos JSON
public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray>{

    int ancho, alto;
    ProgressBar pbConexion;
    ArrayList<Imagen> imagenes;
    GridView gv;

    String url;
    RequestQueue queue;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ligar elementos de la vista
        gv = findViewById(R.id.gv);
        pbConexion = findViewById(R.id.pbConexion);
        imagenes = new ArrayList<Imagen>();

        //Obtener caracteristicas de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();

        //Obtener tamaño de la pantalla
        display.getSize(point);

        //Guardar ancho y alto de pantalla
        ancho = point.x;
        alto = point.y;

        //Generar cola de conexiones
        queue = Volley.newRequestQueue(this);
        //Definicion de la URL del servicio
        url = getResources().getString(R.string.url_base) + "peticion";
        //url = "http://10.0.2.2/ServidorCM/" + "peticion";
        //Generacion del request, almacena los datos de la respuesta
        request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        //Realiza la conexion
        queue.add(request);
    }

    //Metodo para el manejo de errores en la conexion
    @Override
    public void onErrorResponse(VolleyError error) {
        //Quitar ProgressBarr al terminar conexion
        pbConexion.setVisibility(View.GONE);
        Toast.makeText(this, getResources().getString(R.string.errorConexion), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onResponse(JSONArray response) {
        //Quitar ProgressBarr al terminar conexion
        pbConexion.setVisibility(View.GONE);
        //Log.d("Respuesta", response.toString());

        JSONObject json;

        try{
            //Iteracion sobre arreglo de objetos JSON
            for(int i=0; i<response.length(); i++){
                //Se recupera el elemento JSON
                json = response.getJSONObject(i);

                //Recupera valor de etiqueta id
                int id = json.getInt("id");
                //Recupera valor de etiqueta url_img
                String url_img = json.getString("url_img");

                //Nueva instancia de imagen
                Imagen imagen = new Imagen(id, url_img);

                //Agrega imagen al ArrayList
                imagenes.add(imagen);
            }

            //Instancia del adaptador, usa el arreglo de imagenes
            ImageAdapter adapter = new ImageAdapter(this, ancho, imagenes);
            //Asocia adaptador con el GridView
            gv.setAdapter(adapter);
            //Establece numero de columnas del GridView
            gv.setNumColumns(3);

            //Metodo listener para identificar objetos seleccionados
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Intent para indicar siguiente Activity
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                    //Bundle para almacenar informacion del Activity actual
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", id);

                    //Guardar informacion en el intent
                    intent.putExtras(bundle);

                    //Inicia la nueva Activity
                    startActivity(intent);
                }
            });
        }catch (JSONException e){

        }
    }
}