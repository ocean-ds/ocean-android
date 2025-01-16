package br.com.useblu.oceands.components.daterangefilter

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.OceanBottomSheetCompose
import br.com.useblu.oceands.components.OceanDatePickerFullscreen
import br.com.useblu.oceands.components.compose.input.OceanTextInput
import br.com.useblu.oceands.extensions.getSupportFragmentManager
import br.com.useblu.oceands.extensions.isValidDate
import br.com.useblu.oceands.extensions.oceanFormat
import br.com.useblu.oceands.extensions.toDate
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType
import br.com.useblu.oceands.utils.OceanIcons
import java.util.Calendar
import java.util.Date

internal class OceanDateRangeSelectFilterSheet(
    currentBeginDate: String,
    currentEndDate: String,
    private val maxDate: Calendar? = null,
    private val datePattern: String = "dd/MM/yyyy",
    private val context: Context
) {
    private var uiState by mutableStateOf(
        OceanDateRangeSelectFilterUIState(
        beginDate = currentBeginDate,
        endDate = currentEndDate
    )
    )

    fun showBottomSheet(onResult: (String, String) -> Unit) {
        OceanBottomSheetCompose()
            .withActionPositive(
                text = context.getString(R.string.date_range_button_confirm),
                callBack = {
                    onResult(uiState.beginDate, uiState.endDate)
                },
                validation = ::isDateRangeValid
            )
            .withActionNegative(
                text = context.getString(R.string.date_range_button_clear),
                callBack = {
                    onResult("", "")
                }
            )
            .withComposeContent { DateRangeContent() }
            .show(context.getSupportFragmentManager(),null)
    }

    @Composable
    private fun DateRangeContent() {
        Column {
            Text(
                text = context.getString(R.string.date_range_filter_title),
                style = OceanTextStyle.heading4,
                color = OceanColors.interfaceDarkDeep
            )

            OceanSpacing.StackXS()

            OceanTextInput(
                oceanInputType = OceanInputType.Date,
                trailingIcon = OceanIcons.CALENDAR_OUTLINE,
                labelColor = OceanColors.interfaceDarkDown,
                errorText = uiState.errorBeginDate,
                value = uiState.beginDate,
                label = context.getString(R.string.date_range_begin_date),
                placeholder = datePattern.lowercase(),
                onClickTrailingIcon = { onEvent(OceanDateRangeSelectFilterEvent.OnClickBeginDateCalendar) },
                onTextChanged = { text ->
                    onEvent(OceanDateRangeSelectFilterEvent.OnTextChangedBeginDate(text))
                }
            )

            OceanSpacing.StackXS()

            OceanTextInput(
                oceanInputType = OceanInputType.Date,
                trailingIcon = OceanIcons.CALENDAR_OUTLINE,
                labelColor = OceanColors.interfaceDarkDown,
                errorText = uiState.errorEndDate,
                value = uiState.endDate,
                label = context.getString(R.string.date_range_end_date),
                placeholder = datePattern.lowercase(),
                onClickTrailingIcon = { onEvent(OceanDateRangeSelectFilterEvent.OnClickEndDateCalendar) },
                onTextChanged = { text ->
                    onEvent(OceanDateRangeSelectFilterEvent.OnTextChangedEndDate(text))
                }
            )
        }
    }

    @VisibleForTesting
    fun isDateRangeValid(): Boolean {
        val beginDate = uiState.beginDate
        val endDate = uiState.endDate
        var hasErrors = false

        if (beginDate.isBlank() && endDate.isBlank()) {
            return true
        }

        if (beginDate.isValidDate(pattern = datePattern).not() || datePattern.length < 10) {
            uiState = uiState.copy(
                errorBeginDate = context.getString(R.string.date_range_invalid_date_error)
            )
            hasErrors = true
        }

        if (endDate.isValidDate(pattern = datePattern).not() || datePattern.length < 10) {
            uiState = uiState.copy(
                errorEndDate = context.getString(R.string.date_range_invalid_date_error)
            )
            hasErrors = true
        }

        if (beginDate.isBlank()) {
            uiState = uiState.copy(
                errorBeginDate = context.getString(R.string.date_range_empty_error)
            )
            hasErrors = true
        }

        if (endDate.isBlank()) {
            uiState = uiState.copy(
                errorEndDate = context.getString(R.string.date_range_empty_error)
            )
            hasErrors = true
        }

        if (hasErrors.not()) {
            val beginDateParsed = beginDate.toDate(pattern = datePattern) ?: Date()
            val endDateParsed = endDate.toDate(pattern = datePattern) ?: Date()
            if (beginDateParsed > endDateParsed) {
                uiState = uiState.copy(
                    errorBeginDate = context.getString(R.string.date_range_invalid_range_error)
                )
                hasErrors = true
            }
        }

        return !hasErrors
    }

    @VisibleForTesting
    fun onEvent(event: OceanDateRangeSelectFilterEvent) {
        when (event) {
            is OceanDateRangeSelectFilterEvent.OnTextChangedBeginDate -> {
                uiState = uiState.copy(beginDate = event.text)
            }
            is OceanDateRangeSelectFilterEvent.OnTextChangedEndDate -> {
                uiState = uiState.copy(endDate = event.text)
            }
            is OceanDateRangeSelectFilterEvent.OnClickBeginDateCalendar -> {
                showDatePicker(title = context.getString(R.string.date_range_begin_date)) { beginDate ->
                    uiState = uiState.copy(beginDate = beginDate)
                }
            }
            is OceanDateRangeSelectFilterEvent.OnClickEndDateCalendar -> {
                showDatePicker(title = context.getString(R.string.date_range_end_date)) { endDate ->
                    uiState = uiState.copy(endDate = endDate)
                }
            }
        }
    }

    private fun showDatePicker(title: String, onResult: (String) -> Unit) {
        OceanDatePickerFullscreen(manager = context.getSupportFragmentManager())
            .withTitle(title)
            .withOnConfirm { date ->
                onResult(date.time.oceanFormat(pattern = datePattern))
            }
            .withMaxDate(maxDate = maxDate)
            .show()
    }
}