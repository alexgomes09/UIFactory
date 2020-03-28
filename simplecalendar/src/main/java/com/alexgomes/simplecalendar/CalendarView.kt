package com.alexgomes.simplecalendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.util.*

/*
* Created by Alex Gomes.
* Date: 2020-03-27
* Time: 1:01 PM
*/

class CalendarView : ConstraintLayout {

    private lateinit var gridLayoutManager: GridLayoutManager
    private var calendar: Calendar = Calendar.getInstance()
    private lateinit var gridAdapter: GridAdapter
    private var days: ArrayList<Int> = ArrayList()
    private var currentMonth: Int = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.calendar_view, this)

        gridLayoutManager = GridLayoutManager(context, 7)
        rc_calendarView.layoutManager = gridLayoutManager
        gridAdapter = GridAdapter(context, days, calendar)
        rc_calendarView.adapter = gridAdapter
        rc_calendarView.addItemDecoration(SpaceItemDecoration())

        getCurrentMonth()
        inflateWeeks()

        btnNext.setOnClickListener { getNextMonth() }

        btnPrevious.setOnClickListener { getPreviousMonth() }
    }

    public fun setDatePickerListener(dateFormat: String?, datePickerListener: CalendarDatePicker) {
        gridAdapter.setDatePickerListener(dateFormat, datePickerListener)
    }

    private fun getCurrentMonth() {
        currentMonth = calendar.get(Calendar.MONTH)
        calendar.set(Calendar.MONTH, currentMonth)
        setCurrentMonthText()
        populateDateAndNotifyAdapter()
    }

    private fun getNextMonth() {
        currentMonth = calendar.get(Calendar.MONTH)
        calendar.set(Calendar.MONTH, currentMonth + 1)
        setCurrentMonthText()
        populateDateAndNotifyAdapter()
    }

    private fun getPreviousMonth() {
        currentMonth = calendar.get(Calendar.MONTH)
        calendar.set(Calendar.MONTH, currentMonth - 1)
        setCurrentMonthText()
        populateDateAndNotifyAdapter()
    }

    private fun populateDateAndNotifyAdapter() {
        days.clear()
        for (i in 1 until calendar.get(Calendar.DAY_OF_WEEK)) {
            days.add(0)
        }
        for (i in 1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            days.add(i)
        }
        gridAdapter.notifyDataSetChanged()
    }

    private fun setCurrentMonthText() {
        getFirstDayOfMonth()
        current_month.text = String.format(
            "%s %s",
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CANADA),
            calendar.get(Calendar.YEAR)
        )
    }

    private fun getFirstDayOfMonth() {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
    }

    private fun inflateWeeks() {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.weight = 1f

        for (element in resources.getStringArray(R.array.name_of_week)) {
            val textView = TextView(context)
            textView.setTextColor(Color.BLACK)
            textView.text = element.substring(0, 3)
            textView.layoutParams = params
            textView.gravity = Gravity.CENTER
            day_of_week.addView(textView)
        }
    }
}