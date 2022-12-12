package com.example.alarmclock

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class Stopwatch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        window.decorView.setOnTouchListener(object : OnSwipeTouchListener(this@Stopwatch) {
            override fun onSwipeRight() {
                val dataIntent1 = Intent(this@Stopwatch, Timer::class.java).apply {
                }
                startActivity(dataIntent1)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        })

        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

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


