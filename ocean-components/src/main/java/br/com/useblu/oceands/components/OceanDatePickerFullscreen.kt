package br.com.useblu.oceands.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanDatePickerFullscreenBinding
import br.com.useblu.oceands.utils.DisabledDaysDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
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
            binding.calendarView.addDecorators(DisabledDaysDecorator(it))
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

    fun withOnConfirm(onConfirm: (Calendar) -> Unit): OceanDatePickerFullscreen {
        this.onClickConfirm = onConfirm
        return this
    }

    fun show() {
        this.show(manager, null)
    }
}
