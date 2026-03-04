package br.com.useblu.oceands.utils

import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

private const val TOOLTIP_SHOW_DELAY_MS = 150L

/**
 * Finds the DayView for the given [date] within the MaterialCalendarView hierarchy
 * and invokes [onFound] with it. Uses reflection since DayView is package-private.
 * Falls back to the calendar view itself if the day view is not found.
 * Uses a short delay before invoking [onFound] so the selection touch event completes
 * before showing the tooltip, preventing Balloon from immediately dismissing due to
 * setDismissWhenTouchOutside interpreting the tap as an outside touch.
 */
fun MaterialCalendarView.findDayViewByDate(date: CalendarDay, onFound: (View) -> Unit) {
    post {
        val dayView = findDayViewRecursive(this, date)
        val targetView = dayView ?: return@post
        postDelayed({ onFound(targetView) }, TOOLTIP_SHOW_DELAY_MS)
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
