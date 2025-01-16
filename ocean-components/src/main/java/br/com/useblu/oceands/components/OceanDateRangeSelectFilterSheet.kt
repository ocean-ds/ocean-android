package br.com.useblu.oceands.components

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.useblu.oceands.components.compose.input.OceanTextInput
import br.com.useblu.oceands.extensions.getSupportFragmentManager
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType
import br.com.useblu.oceands.utils.OceanIcons

internal class OceanDateRangeSelectFilterSheet(
    currentBeginDate: String,
    currentEndDate: String,
    val context: Context // TODO: add min/max range
) {
    private var uiState by mutableStateOf(OceanDateRangeSelectFilterUIState(
        beginDate = currentBeginDate,
        endDate = currentEndDate
    ))

    fun showBottomSheet(onResult: (String, String) -> Unit) {
        OceanBottomSheetCompose()
            .withActionPositive(
                text = "Filtrar(add)",
                callBack = {
                    onResult(uiState.beginDate, uiState.endDate)
                },
                validation = ::isDateRangeValid
            )
            .withActionNegative(
                text = "Limpar(add)",
                callBack = {
                    onResult("", "")
                }
            )
            .withComposeContent { DateRangeContent() }
            .show(context.getSupportFragmentManager(),null)
    }

    @Composable
    private fun DateRangeContent() {
        Column(
//            modifier = Modifier
//                .padding(horizontal = OceanSpacing.xs)
        ) {
            Text(
                text = "Período(add)",
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
                label = "Data inicial(add)",
                placeholder = "dd/mm/aaaa",
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
                label = "Data final(add)",
                placeholder = "dd/mm/aaaa",
                onClickTrailingIcon = { onEvent(OceanDateRangeSelectFilterEvent.OnClickEndDateCalendar) },
                onTextChanged = { text ->
                    onEvent(OceanDateRangeSelectFilterEvent.OnTextChangedEndDate(text))
                }
            )
        }
    }

    @VisibleForTesting
    fun isDateRangeValid(): Boolean {
        return uiState.beginDate.isNotEmpty() && uiState.endDate.isNotEmpty()
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
                OceanDatePickerFullscreen(manager = context.getSupportFragmentManager())
                    .withTitle("Teste") // TODO: add min/max range
                    .withOnConfirm { date ->
                        uiState = uiState.copy(beginDate = date.toString()) // TODO: format date
                    }
                    .show()
            }
            is OceanDateRangeSelectFilterEvent.OnClickEndDateCalendar -> {
                OceanDatePickerFullscreen(manager = context.getSupportFragmentManager())
                    .withTitle("Teste") // TODO: add min/max range
                    .withOnConfirm { date ->
                        uiState = uiState.copy(endDate = date.toString()) // TODO: format date
                    }
                    .show()
            }
        }
    }
}

internal data class OceanDateRangeSelectFilterUIState(
    val beginDate: String = "",
    val endDate: String = "",
    val errorBeginDate: String? = null,
    val errorEndDate: String? = null
)

internal sealed interface OceanDateRangeSelectFilterEvent {
    data class OnTextChangedBeginDate(val text: String) : OceanDateRangeSelectFilterEvent
    data class OnTextChangedEndDate(val text: String) : OceanDateRangeSelectFilterEvent
    data object OnClickBeginDateCalendar : OceanDateRangeSelectFilterEvent
    data object OnClickEndDateCalendar : OceanDateRangeSelectFilterEvent
}