package com.example.notif_pruebas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FireBaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        //Guardar el token generado
        String token = s;
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //Codigo para procesar la notificacion
        //Obtiene el remitente de la notificacion
        String from = remoteMessage.getFrom();

        //Valida que la notificacion no esté vacia
        if(remoteMessage.getNotification() != null){
            mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        //Valida si la notificacion contiene datos
        if(remoteMessage.getData().size() > 0){
            Map data = remoteMessage.getData();
        }
    }

    //Metodo para mostrar la notificacion
    private void mostrarNotificacion(String title, String body) {
        //Intent para abrir la aplicacion al hacer clic en la notificacion
        Intent intent = new Intent(this, MainActivity.class);
        //Limpiar la pila de Activities al abrir la aplicacion
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Invocacion pendiente
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        //Notificacion con sonido predeterminado del dispositivo
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Crear una notificacion
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, "M_CH_ID")
                .setSmallIcon(R.mipmap.ic_launcher)     //Indica icono a usar
                .setContentTitle(title)                 //Establece el titulo
                .setContentText(body)                   //Establece el cuerpo
                .setAutoCancel(true)                    //Borra notificacion al hacer clic
                .setSound(soundUri)                     //Establece sonido
                .setContentIntent(pendingIntent);       //Pasa el intent establecido

        //Uso del servicio de notificaciones para ser agregada al sistema
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
    }
}
