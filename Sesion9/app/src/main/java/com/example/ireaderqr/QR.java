package com.example.ireaderqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import java.net.MalformedURLException;
import java.net.URL;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int PERMISO_CAMARA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ejecucion de la libreria de QR, crea un nuevo obajeto ScannerView
        scannerView =  new ZXingScannerView(this);
        //Agrega a la vista la camara
        setContentView(scannerView);

        //Verificar version de android del dispositivo, debe ser mayor a nivel 23, Android Marshmallow
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checarPermiso()){
                //El permiso se concedió
            }else{
                solicitarPermiso();
            }
        }
        //Asocia metodo al Scanner para manejar resultados
        scannerView.setResultHandler(this);
        //Inicia la ejecucion de la camara
        scannerView.startCamera();
    }

    private void solicitarPermiso() {
        //Solicita permiso en tiempo de ejecucion, uso de camara
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISO_CAMARA);
    }

    private boolean checarPermiso() {
        //Verificar si un permiso esta activo
        return (ContextCompat.checkSelfPermission(QR.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    //Realiza el manejo de resultados de leer el QR
    @Override
    public void handleResult(Result result) {
        //Muestra Log para indicar el URL
        Log.d("QR_LEIDO", result.getText());
        //Obtiene el texto leido en el QR
        final String scanResult = result.getText();

        try{
            //Crea un objeto URL con la cadena leida
            URL url = new URL(scanResult);
            //Abre una accion asociada al Intent, abre accion con aplicacion predeterminada
            Intent i = new Intent(Intent.ACTION_VIEW);
            //Parse el URL para ser usado como direccion
            i.setData(Uri.parse(scanResult));
            //Inicia nuevo Activity, navegador
            startActivity(i);
            //Finaliza Activity actual
            finish();
        }
        //Falló la creacion del URL
        catch (MalformedURLException e) {
            //Crea un nuevo dialogo
            new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.error))
                .setMessage(getResources().getString(R.string.errorUrl))
                .setPositiveButton(getResources().getString(R.string.btnAceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                }).create().show();
        }
    }

    //Muestra un mensaje de permisos de manera personalizada
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISO_CAMARA:
                //Verifica que hay un elemento en el arreglo de permisos solicitados
                if(grantResults.length > 0){
                    //Verifica si el elemento esta en los permisos solicitados
                    if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                        //Verifica la version de Android del dispositivo
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            //Indica el permiso que se desea solicitar al usuario
                            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                                //Mostrar un dialogo con mensaje
                                new AlertDialog.Builder(QR.this)
                                .setTitle(getResources().getString(R.string.permiso))
                                .setMessage(getResources().getString(R.string.mensaje))
                                .setPositiveButton(getResources().getString(R.string.btnAceptar), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Solicita permiso al usuario
                                        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISO_CAMARA);
                                    }
                                })
                                .setNegativeButton(getResources().getString(R.string.btnAceptar), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Quita el dialogo de la pantalla
                                        dialog.dismiss();
                                        //Finaliza el Activity
                                        finish();
                                    }
                                })
                                .create().show();
                            }
                            else{
                                //Mensaje cuando el permiso no se concedio por el usuario
                                Toast.makeText(this, getResources().getString(R.string.msjToast), Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Detiene la camara al destruir Activity
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Revisa la version de Android del dispositivo
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Revisa si el permiso esta activo
            if(checarPermiso()){
                //Verifica que el ScannerView exista
                if(scannerView == null){
                    //Ejecucion de la libreria de QR, nuevo objeto Scanner
                    scannerView =  new ZXingScannerView(this);
                    //Agrega a la vista la camara
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
        }
    }
}