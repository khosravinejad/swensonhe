package com.morteza.swensonhe.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {
    private val calendar = Calendar.getInstance(TimeZone.getDefault())
    fun getHoursAndMinutes(time: Long): String {
        calendar.timeInMillis = time
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
    }

    fun getWeekDay(time: Long): String {
        calendar.timeInMillis = time
        return SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
    }

    fun getWeekDayForecast(timeMillis: Long): String {
        return if (DateUtils.isToday(timeMillis)) {
            "Today"
        } else if (DateUtils.isToday(timeMillis - DateUtils.DAY_IN_MILLIS)) {
            "Tomorrow"
        } else {
            getWeekDay(timeMillis)
        }
    }

    fun getFormattedCurrentLocalTime(): String {
        calendar.timeInMillis = System.currentTimeMillis()
        return SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.time)
    }

    fun getFormattedCurrentLocalDate(): String {
        calendar.timeInMillis = System.currentTimeMillis()
        return SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault()).format(calendar.time)
    }
}