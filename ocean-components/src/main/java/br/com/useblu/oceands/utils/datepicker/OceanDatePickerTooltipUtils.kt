package br.com.useblu.oceands.utils.datepicker

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar
import java.util.Date

fun CalendarDay.toDayOfYearKey(): String {
    val calendar = Calendar.getInstance().apply {
        set(year, month - 1, day)
    }
    return calendar.toDayOfYearKey()
}

fun Date.toDayOfYearKey(): String {
    val calendar = Calendar.getInstance().apply {
        time = this@toDayOfYearKey
    }
    return calendar.toDayOfYearKey()
}

fun Calendar.toDayOfYearKey(): String {
    val year = get(Calendar.YEAR)
    val dayOfYear = get(Calendar.DAY_OF_YEAR)
    return "%04d-%03d".format(year, dayOfYear)
}

fun String.toCalendarDay(): CalendarDay? {
    val parts = split("-")
    if (parts.size != 2) return null
    val year = parts[0].toIntOrNull() ?: return null
    val dayOfYear = parts[1].toIntOrNull() ?: return null
    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.DAY_OF_YEAR, dayOfYear)
    }
    return CalendarDay.from(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}

fun CalendarDay.isDisabled(disabledDays: Array<Calendar>): Boolean {
    return disabledDays.any {
        it.get(Calendar.DAY_OF_MONTH) == day &&
            it.get(Calendar.MONTH) + 1 == month &&
            it.get(Calendar.YEAR) == year
    }
}

fun CalendarDay.isDisabled(disabledDays: List<Date>): Boolean {
    if (disabledDays.isEmpty()) return false
    val cal = Calendar.getInstance()
    return disabledDays.any {
        cal.time = it
        cal.get(Calendar.DAY_OF_MONTH) == day &&
            cal.get(Calendar.MONTH) + 1 == month &&
            cal.get(Calendar.YEAR) == year
    }
}
