package com.example.alarmclock

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {

            override fun onSwipeLeft() {
                val dataIntent = Intent(this@MainActivity, Timer::class.java).apply {
                }
                startActivity(dataIntent)
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
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
    override fun onBackPressed() {
        super.onBackPressed()
    }
}


