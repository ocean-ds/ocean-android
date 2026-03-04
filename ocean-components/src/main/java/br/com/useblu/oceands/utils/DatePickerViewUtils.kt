package br.com.useblu.oceands.utils

import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

fun MaterialCalendarView.findDayViewByDate(date: CalendarDay, onFound: (View) -> Unit) {
    post {
        val dayView = findDayViewRecursive(this, date)
        val targetView = dayView ?: return@post
        onFound(targetView)
    }
}

private fun findDayViewRecursive(root: View, targetDate: CalendarDay): View? {
    if (root is ViewGroup) {
        for (i in 0 until root.childCount) {
            findDayViewRecursive(root.getChildAt(i), targetDate)?.let { return it }
        }
    }
    return try {
        val getDate = root.javaClass.getMethod("getDate")
        val viewDate = getDate.invoke(root)
        if (viewDate == targetDate) root else null
    } catch (_: Exception) {
        null
    }
}
