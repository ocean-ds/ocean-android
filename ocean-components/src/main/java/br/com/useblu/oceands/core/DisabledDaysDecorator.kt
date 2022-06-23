package br.com.useblu.oceands.core

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class DisabledDaysDecorator(private val disabledDays: Array<Calendar>) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date(day.year, day.month - 1, day.day)
        return disabledDays.any {
            val dayOfMonth = it.get(Calendar.DAY_OF_MONTH)
            val month = it.get(Calendar.MONTH) + 1
            val year = it.get(Calendar.YEAR)
            return day.year == year && day.month == month && day.day == dayOfMonth
        }
    }

    override fun decorate(view: DayViewFacade) {
        view.setDaysDisabled(true)
    }
}