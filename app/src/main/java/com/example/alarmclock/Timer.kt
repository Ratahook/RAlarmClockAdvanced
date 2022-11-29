package com.example.alarmclock

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.text.method.Touch.onTouchEvent
import android.widget.TimePicker

class Timer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_timer)

        val timePicker = findViewById<TimePicker>(R.id.picker)
        val timer = findViewById<Chronometer>(R.id.timer)
        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val transition = findViewById<Button>(R.id.transition)

        transition.setOnClickListener {
            val dataIntent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(dataIntent)
        }

        timePicker.setIs24HourView(true)

        timer.isCountDown = true
        timer.base = SystemClock.elapsedRealtime()

        startButton.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime() + timePicker.hour.toLong() * 1000 * 3600 + timePicker.minute.toLong() * 1000 * 60
            timer.start()
        }

        stopButton.setOnClickListener {
            timer.stop()
        }

        resetButton.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime()
        }

        }
    }

