package br.com.useblu.oceands.utils

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import br.com.useblu.oceands.components.OceanTooltip
import br.com.useblu.oceands.model.OceanDatePickerTooltipSetup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar

class OceanDatePickerSelectionHandler(
    private var lastValidSelectedDate: CalendarDay? = null,
    private val tooltipSetups: Map<String, OceanDatePickerTooltipSetup>,
    private val isDisabled: (CalendarDay) -> Boolean,
    private val context: Context?,
    private val lifecycleOwner: LifecycleOwner
) {

    fun getListener() = OnDateSelectedListener { widget, date, _ -> onDateSelected(widget, date) }

    private fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay) {
        lastValidSelectedDate = handleDateSelection(
            widget = widget,
            date = date,
            isDisabled = isDisabled(date)
        )
    }

    private fun showDatePickerTooltip(
        anchorView: View,
        setup: OceanDatePickerTooltipSetup
    ) {
        context ?: return
        OceanTooltip(context = context, lifecycle = lifecycleOwner)
            .withMessage(setup.message)
            .withAutoDismissDuration(setup.getAutoDismissDuration())
            .build()
            .showAlignTop(anchorView)
    }

    private fun handleDateSelection(
        widget: MaterialCalendarView,
        date: CalendarDay,
        isDisabled: Boolean
    ): CalendarDay? = with(widget) {
        val newLastValid = if (isDisabled) {
            if (lastValidSelectedDate != null) {
                selectedDate = lastValidSelectedDate
                lastValidSelectedDate
            } else {
                val today = Calendar.getInstance()
                val todayDay = CalendarDay.from(
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH) + 1,
                    today.get(Calendar.DAY_OF_MONTH)
                )
                selectedDate = todayDay
                todayDay
            }
        } else {
            date
        }

        tooltipSetups[date.toDayOfYearKey()]?.let { setup ->
            findDayViewByDate(date) { anchorView ->
                showDatePickerTooltip(anchorView, setup)
            }
        }

        return newLastValid
    }
}
