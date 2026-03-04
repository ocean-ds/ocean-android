package br.com.useblu.oceands.utils

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.Calendar

class DisabledDaysDecorator(
    private val context: Context,
    private val disabledDays: Array<Calendar>
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return disabledDays.any {
            val disabledDay = it.get(Calendar.DAY_OF_MONTH)
            val disabledMonth = it.get(Calendar.MONTH) + 1
            val disabledYear = it.get(Calendar.YEAR)
            disabledDay == day.day && disabledMonth == day.month && disabledYear == day.year
        }
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.ocean_color_interface_dark_up)))
    }
}
