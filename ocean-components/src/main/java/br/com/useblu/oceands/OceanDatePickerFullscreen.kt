package br.com.useblu.oceands

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import br.com.useblu.oceands.databinding.OceanDatePickerFullscreenBinding
import java.util.*

class OceanDatePickerFullscreen(
    private val manager: FragmentManager
) : DialogFragment() {

    private var title: String? = null
    private var minDate: Long? = null
    private var maxDate: Long? = null
    private var defaultSelected: Long? = null
    private var onClickConfirm: (Date) -> Unit = {}
    private var calendar: Calendar = Calendar.getInstance()
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
        minDate?.let {
            binding.calendarView.minDate = it
        }
        maxDate?.let {
            binding.calendarView.maxDate = it
        }
        defaultSelected?.let {
            binding.calendarView.date = it
        }
        binding.calendarView.setOnDateChangeListener { calView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            calView.setDate(calendar.timeInMillis, true, true)
        }

    }

    private fun setupButton() {
        binding.primaryPositiveButton.click = {
            dismiss()
            onClickConfirm.invoke(calendar.time)
        }
    }

    fun withTitle(title: String): OceanDatePickerFullscreen {
        this.title = title
        return this
    }

    fun withDates(
        minDate: Date? = null,
        maxDate: Date? = null,
        defaultSelected: Date,
        onConfirm: (Date) -> Unit
    ): OceanDatePickerFullscreen {
        this.minDate = minDate?.time
        this.maxDate = maxDate?.time
        this.defaultSelected = defaultSelected.time
        this.onClickConfirm = onConfirm
        return this
    }

    fun show() {
        this.show(manager, null)
    }


}