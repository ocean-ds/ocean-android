package br.com.useblu.oceands.utils.datepicker.decorators

import android.content.Context
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class SelectedDayDecorator(
    private val context: Context,
    private val getSelectedDay: () -> CalendarDay?
) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean = day == getSelectedDay() && day != null

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_interface_light_pure
                )
            )
        )
        view?.addSpan(
            StyleSpan(R.style.Ocean_Description, 700)
        )
    }
}
