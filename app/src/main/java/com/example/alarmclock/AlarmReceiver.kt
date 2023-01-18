package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.io.FileNotFoundException
import java.io.IOException

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
            )

            setWakeMode(context.applicationContext, PowerManager.PARTIAL_WAKE_LOCK)

            try {
                var nameSoundFile = "sound_file_deusex"
                val idRawSound = context.resources.getIdentifier(nameSoundFile, "raw", context.packageName)
                val afd: AssetFileDescriptor = context.resources.openRawResourceFd(idRawSound)
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                prepare()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val builder = NotificationCompat.Builder(context, "wakeup")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Ratahook wake up")
            .setContentText("О нет, Ратахуку пора вставать")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())

        mediaPlayer.start()



    }
}