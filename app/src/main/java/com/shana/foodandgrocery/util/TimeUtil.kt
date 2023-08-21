package com.shana.foodandgrocery.util

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

class TimeUtil {
    companion object{
        fun YearMonth.displayText(short: Boolean = false): String {
            return "${this.month.displayText(short = short)} ${this.year}"
        }

        fun Month.displayText(short: Boolean = true): String {
            val style = if (short) TextStyle.SHORT else TextStyle.FULL
            return getDisplayName(style, Locale.ENGLISH)
        }
        fun DayOfWeek.displayText(uppercase: Boolean = false): String {
            return getDisplayName(TextStyle.SHORT, Locale.ENGLISH).let { value ->
                if (uppercase) value.uppercase(Locale.ENGLISH) else value
            }
        }
        fun LocalDateTime.toMillis()=this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}
