package br.com.useblu.oceands.utils.datepicker

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.OceanTooltip
import br.com.useblu.oceands.model.OceanDatePickerTooltipSetup
import br.com.useblu.oceands.utils.datepicker.decorators.DisabledDaysDecorator
import br.com.useblu.oceands.utils.datepicker.decorators.SelectedDayDecorator
import br.com.useblu.oceands.utils.datepicker.decorators.TodayDayDecorator
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

    companion object {
        fun setupCalendar(
            context: Context,
            widget: MaterialCalendarView,
            minDate: CalendarDay? = null,
            maxDate: CalendarDay? = null,
            disabledDays: Array<Calendar> = emptyArray()
        ) {
            widget.setLeftArrow(R.drawable.ocean_icon_chevron_left_outline_primary)
            widget.setRightArrow(R.drawable.ocean_icon_chevron_right_outline_primary)
            widget.showOtherDates = MaterialCalendarView.SHOW_OUT_OF_RANGE
            widget.setHeaderTextAppearance(R.style.Ocean_Heading_4)
            widget.setWeekDayTextAppearance(R.color.ocean_color_interface_dark_down)
            widget.setWeekDayLabels(R.array.ocean_week_day_labels)
            widget.setTitleMonths(R.array.ocean_month_labels)
            widget.setDateTextAppearance(R.style.Ocean_Description)
            widget.selectionColor = context.getColor(R.color.ocean_color_complementary_pure)

            val calendarState = widget.state().edit()
            minDate?.let { calendarState.setMinimumDate(it) }
            maxDate?.let { calendarState.setMaximumDate(it) }
            calendarState.commit()
            widget.addDecorators(
                TodayDayDecorator(context),
                DisabledDaysDecorator(
                    context = context,
                    disabledDays = disabledDays,
                    minDate = minDate,
                    maxDate = maxDate
                ),
                SelectedDayDecorator(
                    context = context,
                    getSelectedDay = { widget.selectedDate }
                )
            )
        }
    }

    fun getListener() = OnDateSelectedListener { widget, date, _ -> onDateSelected(widget, date) }

    fun showTooltipsOnOpen(widget: MaterialCalendarView) {
        tooltipSetups.forEach { (key, setup) ->
            if (!setup.showOnOpen) return@forEach
            val date = key.toCalendarDay() ?: return@forEach
            widget.findDayViewByDate(date) { anchorView ->
                showDatePickerTooltip(anchorView, setup)
            }
        }
    }

    private fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay) {
        lastValidSelectedDate = handleDateSelection(
            widget = widget,
            date = date,
            isDisabled = isDisabled(date)
        )
        widget.invalidateDecorators()
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
            if (!setup.showOnTap) return@let
            findDayViewByDate(date) { anchorView ->
                showDatePickerTooltip(anchorView, setup)
            }
        }

        return newLastValid
    }
}
