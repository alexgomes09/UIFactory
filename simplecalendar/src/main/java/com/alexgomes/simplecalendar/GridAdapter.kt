package com.alexgomes.simplecalendar

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by alexgomes on 2017-05-30.
 */

class GridAdapter constructor(
    private val context: Context,
    private val days: ArrayList<Int>,
    private val calendar: Calendar
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var calendarDatePicker: CalendarDatePicker? = null
    private lateinit var simpleDateFormat: SimpleDateFormat

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false)
        return if (viewType == 0) {
            EmptyHolder(view)
        } else {
            DayHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (days[position] == 0) {
            (holder as EmptyHolder).itemName.text = ""
        } else {
            (holder as DayHolder).itemName.text = "${days[position]}"
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (days[position] == 0) {
            0
        } else {
            1
        }
    }

    fun setDatePickerListener(dateFormat: String?, calendarDatePicker: CalendarDatePicker) {
        Log.v("==TAG==", "setDatePickerListener $dateFormat")
        this.simpleDateFormat = SimpleDateFormat(dateFormat, Locale.US)
        this.calendarDatePicker = calendarDatePicker
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return days.size
    }

    internal inner class DayHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var itemName: TextView = itemView.findViewById<View>(R.id.day) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (adapterPosition == RecyclerView.NO_POSITION) return
            if (!::simpleDateFormat.isInitialized) return

            val dateString = simpleDateFormat.format(calendar.time).toString()
            calendarDatePicker?.onItemClick(dateString)
        }
    }

    internal inner class EmptyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById<View>(R.id.day) as TextView

    }
}
