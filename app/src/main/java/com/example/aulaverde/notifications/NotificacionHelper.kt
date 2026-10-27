package com.example.aulaverde.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.aulaverde.R

object NotificacionHelper {

    private const val CANAL_ID = "aulaverde_bienvenida"

    fun mostrarBienvenida(context: Context) {

        val manager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                CANAL_ID,
                "Bienvenida AulaVerde",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            manager.createNotificationChannel(canal)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permiso = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            )

            if (permiso != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }

        val notificacion = NotificationCompat.Builder(
            context,
            CANAL_ID
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("¡Bienvenido a AulaVerde!")
            .setContentText(
                "Tu cuenta de profesor se registró correctamente."
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(
            1001,
            notificacion
        )
    }
}
