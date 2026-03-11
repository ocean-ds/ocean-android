package br.com.useblu.oceands.utils.datepicker.decorators

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.R
import br.com.useblu.oceands.utils.datepicker.isDisabled
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.Calendar

class DisabledDaysDecorator(
    private val context: Context,
    private val disabledDays: Array<Calendar>,
    private val minDate: CalendarDay?,
    private val maxDate: CalendarDay?
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return if (!day.isInRange(minDate, maxDate)) true else day.isDisabled(disabledDays)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_light_deep
                )
            )
        )
    }
}
