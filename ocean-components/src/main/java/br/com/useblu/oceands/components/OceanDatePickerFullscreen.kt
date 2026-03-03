package br.com.useblu.oceands.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanDatePickerFullscreenBinding
import br.com.useblu.oceands.model.OceanDatePickerTooltipSetup
import br.com.useblu.oceands.utils.DisabledDaysDecorator
import br.com.useblu.oceands.utils.findDayViewByDate
import br.com.useblu.oceands.utils.isDisabled
import br.com.useblu.oceands.utils.toDayOfYearKey
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class OceanDatePickerFullscreen(
    private val manager: FragmentManager
) : DialogFragment() {

    private var title: String? = null
    private var minDate: Calendar? = null
    private var maxDate: Calendar? = null
    private var defaultSelected: Calendar? = null
    private var disabledDays: Array<Calendar> = emptyArray()
    private var tooltipSetups: Map<String, OceanDatePickerTooltipSetup> = emptyMap()
    private var onClickConfirm: (Calendar) -> Unit = {}
    private var _binding: OceanDatePickerFullscreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Ocean_FullScreenDatePicker)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OceanDatePickerFullscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupCalendar()
        setupButton()
    }

    private fun setupToolbar() {
        binding.toolbar.title = title
        binding.toolbar.click = {
            dismiss()
        }
    }

    private fun setupCalendar() {
        defaultSelected?.let {
            val day = it.get(Calendar.DAY_OF_MONTH)
            val month = it.get(Calendar.MONTH) + 1
            val year = it.get(Calendar.YEAR)
            binding.calendarView.selectedDate = CalendarDay.from(year, month, day)
        }

        val calendarState = binding.calendarView.state().edit()
        minDate?.let {
            val day = it.get(Calendar.DAY_OF_MONTH)
            val month = it.get(Calendar.MONTH) + 1
            val year = it.get(Calendar.YEAR)
            calendarState.setMinimumDate(CalendarDay.from(year, month, day))
        }
        maxDate?.let {
            val day = it.get(Calendar.DAY_OF_MONTH)
            val month = it.get(Calendar.MONTH) + 1
            val year = it.get(Calendar.YEAR)
            calendarState.setMaximumDate(CalendarDay.from(year, month, day))
        }
        calendarState.commit()

        disabledDays.let {
            context?.let { ctx ->
                binding.calendarView.addDecorators(DisabledDaysDecorator(ctx, it))
            }
        }

        var lastValidSelectedDate: CalendarDay? = binding.calendarView.selectedDate
            ?: defaultSelected?.let {
                CalendarDay.from(it.get(Calendar.YEAR), it.get(Calendar.MONTH) + 1, it.get(Calendar.DAY_OF_MONTH))
            }
        binding.calendarView.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                val setup = tooltipSetups[date.toDayOfYearKey()]
                val message = setup?.message ?: getString(R.string.date_picker_disabled_date_tooltip)
                val autoDismissMs = setup?.autoDismissMs ?: Long.MAX_VALUE
                widget.findDayViewByDate(date) { anchorView ->
                    showDateSelectedTooltip(anchorView, OceanDatePickerTooltipSetup(message, autoDismissMs))
                }

                if (!selected) return
                val isDisabled = date.isDisabled(disabledDays)
                if (isDisabled) {
                    if (lastValidSelectedDate != null) {
                        widget.selectedDate = lastValidSelectedDate
                    } else {
                        val today = Calendar.getInstance()
                        widget.selectedDate = CalendarDay.from(
                            today.get(Calendar.YEAR),
                            today.get(Calendar.MONTH) + 1,
                            today.get(Calendar.DAY_OF_MONTH)
                        )
                    }
                } else {
                    lastValidSelectedDate = date
                }
            }
        })
    }

    private fun showDateSelectedTooltip(anchorView: View, setup: OceanDatePickerTooltipSetup) {
        context?.let { ctx ->
            OceanTooltip(context = ctx, lifecycle = viewLifecycleOwner)
                .withMessage(setup.message)
                .withAutoDismissDuration(setup.autoDismissMs ?: Long.MAX_VALUE)
                .build()
                .showAlignTop(anchorView)
        }
    }

    private fun setupButton() {
        binding.primaryPositiveButton.click = {
            dismiss()
            binding.calendarView.selectedDate?.let {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val convertedDate = dateFormat.parse(it.year.toString() + "-" + it.month.toString() + "-" + it.day.toString())
                val calendar = Calendar.getInstance()
                calendar.time = convertedDate ?: Date()
                onClickConfirm.invoke(calendar)
            }
        }
    }

    fun withTitle(title: String): OceanDatePickerFullscreen {
        this.title = title
        return this
    }

    fun withMinDate(minDate: Calendar? = null): OceanDatePickerFullscreen {
        this.minDate = minDate
        return this
    }

    fun withMaxDate(maxDate: Calendar? = null): OceanDatePickerFullscreen {
        this.maxDate = maxDate
        return this
    }

    fun withDefaultSelect(defaultSelected: Calendar): OceanDatePickerFullscreen {
        this.defaultSelected = defaultSelected
        return this
    }

    fun withDisabledDays(disabledDays: Array<Calendar> = emptyArray()): OceanDatePickerFullscreen {
        this.disabledDays = disabledDays
        return this
    }

    fun withTooltipSetups(setups: Map<String, OceanDatePickerTooltipSetup>): OceanDatePickerFullscreen {
        this.tooltipSetups = setups
        return this
    }

    fun withOnConfirm(onConfirm: (Calendar) -> Unit): OceanDatePickerFullscreen {
        this.onClickConfirm = onConfirm
        return this
    }

    fun show() {
        this.show(manager, null)
    }
}
