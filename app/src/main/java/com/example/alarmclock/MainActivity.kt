package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import java.util.*

var mediaPlayer: MediaPlayer = MediaPlayer()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                val dataIntent = Intent(this@MainActivity, Timer::class.java).apply {
                }
                startActivity(dataIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        })

        val timePicker = findViewById<TimePicker>(R.id.picker)
        timePicker.setIs24HourView(true)

        val setAlarm = findViewById<Button>(R.id.setAlarm)
        val stopAlarm = findViewById<Button>(R.id.stopAlarm)

        setAlarm.setOnClickListener {
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AlarmReceiver::class.java)

            val pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val alarmClockInfo = AlarmManager.AlarmClockInfo(
                convertToAlarmTimeMillis(timePicker.hour, timePicker.minute),
                pendingIntent)
            alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)

            Toast.makeText(this, "Будильник установлен", Toast.LENGTH_SHORT).show()
        }

        stopAlarm.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
    }

    private fun convertToAlarmTimeMillis(hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val proposedTimeMillis = calendar.timeInMillis

        val alarmTimeMillis: Long = if (proposedTimeMillis > currentTimeMillis) {
            proposedTimeMillis
        } else {
            proposedTimeMillis + 24 * 60 * 60 * 1000
        }

        return alarmTimeMillis
    }
}