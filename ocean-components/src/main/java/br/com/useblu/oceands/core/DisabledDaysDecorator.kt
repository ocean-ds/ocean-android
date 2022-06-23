package br.com.useblu.oceands.core

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class DisabledDaysDecorator(private val disabledDays: Array<Calendar>) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {

        return disabledDays.any {
            val disabledDay = it.get(Calendar.DAY_OF_MONTH)
            val disabledMonth = it.get(Calendar.MONTH) + 1
            val disabledYear = it.get(Calendar.YEAR)
            disabledDay == day.day && disabledMonth == day.month && disabledYear == day.year
        }
    }

    override fun decorate(view: DayViewFacade) {
        view.setDaysDisabled(true)
    }
}