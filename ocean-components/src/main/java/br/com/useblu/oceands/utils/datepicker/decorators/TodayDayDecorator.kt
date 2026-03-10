package br.com.useblu.oceands.utils.datepicker.decorators

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class TodayDayDecorator(
    private val context: Context
) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean = day == CalendarDay.today()

    override fun decorate(view: DayViewFacade) {
        view.addSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.ocean_color_brand_primary_pure
                )
            )
        )
    }
}
