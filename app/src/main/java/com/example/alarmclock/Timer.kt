package com.example.alarmclock

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.os.VibrationEffect.createOneShot
import android.widget.*

class Timer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        window.decorView.setOnTouchListener(object : OnSwipeTouchListener(this@Timer) {
            override fun onSwipeRight() {
                val dataIntent = Intent(this@Timer, MainActivity::class.java).apply {
                }
                startActivity(dataIntent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }

            override fun onSwipeLeft() {
                val dataIntent1 = Intent(this@Timer, Stopwatch::class.java).apply {
                }
                startActivity(dataIntent1)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        })

        val hourPicker = findViewById<NumberPicker>(R.id.numpicker_hours)
        val minutePicker = findViewById<NumberPicker>(R.id.numpicker_minutes)
        val secondsPicker = findViewById<NumberPicker>(R.id.numpicker_seconds)
        val timer = findViewById<Chronometer>(R.id.timer)
        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val mediaPlayer = MediaPlayer.create(this, R.raw.sound_file_deusex)

        hourPicker.maxValue = 99
        hourPicker.minValue = 0
        hourPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        minutePicker.maxValue = 59
        minutePicker.minValue = 0
        minutePicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        secondsPicker.maxValue = 59
        secondsPicker.minValue = 0
        secondsPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        timer.isCountDown = true
        timer.base = SystemClock.elapsedRealtime()
        timer.setOnChronometerTickListener {
            val currentTime = timer.text.toString()
            if(currentTime == "00:01") {
                timer.stop()
                mediaPlayer.start()
                vibrate(this)
            }
        }

        startButton.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime() + hourPicker.value * 1000 * 3600 + minutePicker.value * 1000 * 60 + secondsPicker.value * 1000
            timer.start()
        }

        stopButton.setOnClickListener {
            timer.stop()
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }

        resetButton.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime()
            timer.stop()
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }

        }
    }

private fun vibrate(ctx: Context?) {
    if (ctx != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                ctx.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
        } else {
            val v = ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.vibrate(VibrationEffect.createOneShot(1000L, 10))
            } else {
                v.vibrate(200L)
            }
        }
    }
}
