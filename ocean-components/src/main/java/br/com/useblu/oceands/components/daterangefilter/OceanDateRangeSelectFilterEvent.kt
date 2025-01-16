package br.com.useblu.oceands.components.daterangefilter

internal sealed interface OceanDateRangeSelectFilterEvent {
    data class OnTextChangedBeginDate(val text: String) : OceanDateRangeSelectFilterEvent
    data class OnTextChangedEndDate(val text: String) : OceanDateRangeSelectFilterEvent
    data object OnClickBeginDateCalendar : OceanDateRangeSelectFilterEvent
    data object OnClickEndDateCalendar : OceanDateRangeSelectFilterEvent
}