package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val builder = NotificationCompat.Builder(context, "wakeup")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Ratahook wake up")
            .setContentText("О нет, Ратахуку пора вставать")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())
    }
}