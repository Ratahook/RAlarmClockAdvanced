package com.example.alarmclock

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.text.method.Touch.onTouchEvent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val transition = findViewById<Button>(R.id.transition)



        transition.setOnClickListener {
            val dataIntent = Intent(this, Timer::class.java).apply {
            }
            startActivity(dataIntent)
        }
        chronometer.setOnChronometerTickListener {
        }

        startButton.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
        }

        stopButton.setOnClickListener {
            chronometer.stop()
        }

        resetButton.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
        }


    }
}

