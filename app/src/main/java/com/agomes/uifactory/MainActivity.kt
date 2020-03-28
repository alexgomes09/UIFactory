package com.agomes.uifactory

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alexgomes.simplecalendar.CalendarDatePicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView.setDatePickerListener(null, object : CalendarDatePicker {
            override fun onItemClick(foratedDate: String) {
                Log.v("==TAG==", "onItemClick $foratedDate")
            }
        })
    }
}
