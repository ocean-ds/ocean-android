package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.DisabledDaysDecorator
import br.com.useblu.oceands.utils.OceanIcons
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.Calendar
import java.util.Date

@Preview
@Composable
private fun OceanDatePickerDialogPreview() {
    OceanDatePickerDialog(
        title = "Selecione uma data",
        infoTitle = "Título",
        infoMessage = "Mensagem",
        maxDate = Date(),
        onConfirm = {},
        onDismiss = {}
    )
}

@Composable
fun OceanDatePickerDialog(
    modifier: Modifier = Modifier,
    title: String,
    infoTitle: String = "",
    infoMessage: String = "",
    minDate: Date? = null,
    maxDate: Date? = null,
    defaultDate: Date = Date(),
    disabledDays: List<Date> = emptyList(),
    onConfirm: (Date) -> Unit,
    onDismiss: () -> Unit
) {
    var calendarView by remember {
        mutableStateOf<MaterialCalendarView?>(null)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = OceanColors.interfaceLightPure)
        ) {
            OceanTopBarInverse(title = title, onClickIcon = onDismiss, icon = OceanIcons.X_OUTLINE)

            if (infoTitle.isNotBlank()) {
                OceanText(
                    modifier = Modifier
                        .padding(horizontal = OceanSpacing.xs)
                        .padding(bottom = OceanSpacing.xxs),
                    text = infoTitle,
                    style = OceanTextStyle.heading4
                )
            }

            if (infoMessage.isNotBlank()) {
                OceanText(
                    modifier = Modifier
                        .padding(horizontal = OceanSpacing.xs)
                        .padding(bottom = OceanSpacing.xs),
                    text = infoMessage,
                    style = OceanTextStyle.description
                )
            }

            AndroidView(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = OceanSpacing.xxs),
                factory = { context ->
                    MaterialCalendarView(context).apply {
                        calendarView = this
                        setHeaderTextAppearance(R.style.Ocean_Heading_4)
                        setWeekDayTextAppearance(R.color.ocean_color_interface_dark_down)
                        selectionColor = context.getColor(R.color.ocean_color_complementary_pure)
                        val defaultDate = Calendar.getInstance().apply {
                            time = defaultDate
                        }
                        val defaultDay = CalendarDay.from(
                            defaultDate.get(Calendar.YEAR),
                            defaultDate.get(Calendar.MONTH) + 1,
                            defaultDate.get(Calendar.DAY_OF_MONTH)
                        )
                        this.selectedDate = defaultDay

                        val calendarState = this.state().edit()
                        if (minDate != null) {
                            val calendar = Calendar.getInstance().apply {
                                time = minDate
                            }
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH) + 1
                            val day = calendar.get(Calendar.DAY_OF_MONTH)
                            calendarState.setMinimumDate(CalendarDay.from(year, month, day))
                        }

                        if (maxDate != null) {
                            val calendar = Calendar.getInstance().apply {
                                time = maxDate
                            }
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH) + 1
                            val day = calendar.get(Calendar.DAY_OF_MONTH)
                            calendarState.setMaximumDate(CalendarDay.from(year, month, day))
                        }

                        if (disabledDays.isNotEmpty()) {
                            this.addDecorators(
                                DisabledDaysDecorator(
                                    disabledDays.map {
                                        Calendar.getInstance().apply {
                                            time = it
                                        }
                                    }.toTypedArray()
                                )
                            )
                        }
                        calendarState.commit()
                    }
                }
            )

            OceanSpacing.StackXS()

            OceanButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(OceanSpacing.xs),
                text = stringResource(id = R.string.button_confirm),
                onClick = {
                    calendarView?.selectedDate?.let {
                        val calendar = Calendar.getInstance().apply {
                            set(it.year, it.month - 1, it.day)
                        }
                        onConfirm(calendar.time)
                    }
                },
                buttonStyle = OceanButtonStyle.PrimaryMedium
            )
        }
    }
}
