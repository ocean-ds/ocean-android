package br.com.useblu.oceands.utils

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar
import java.util.Date

/**
 * Key format: "yyyy-DDD" (e.g., "2025-032") for O(1) map lookup.
 * DDD is zero-padded day of year (1-366).
 */
fun CalendarDay.toDayOfYearKey(): String {
    val calendar = Calendar.getInstance().apply {
        set(year, month - 1, day)
    }
    return calendar.toDayOfYearKey()
}

/**
 * Key format: "yyyy-DDD" (e.g., "2025-032") for O(1) map lookup.
 */
fun Date.toDayOfYearKey(): String {
    val calendar = Calendar.getInstance().apply {
        time = this@toDayOfYearKey
    }
    return calendar.toDayOfYearKey()
}

/**
 * Key format: "yyyy-DDD" (e.g., "2025-032") for O(1) map lookup.
 */
fun Calendar.toDayOfYearKey(): String {
    val year = get(Calendar.YEAR)
    val dayOfYear = get(Calendar.DAY_OF_YEAR)
    return "%04d-%03d".format(year, dayOfYear)
}

/**
 * Returns true if this CalendarDay is in the disabled days list.
 */
fun CalendarDay.isDisabled(disabledDays: Array<Calendar>): Boolean {
    return disabledDays.any {
        it.get(Calendar.DAY_OF_MONTH) == day &&
            it.get(Calendar.MONTH) + 1 == month &&
            it.get(Calendar.YEAR) == year
    }
}

/**
 * Returns true if this CalendarDay is in the disabled days list.
 */
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
