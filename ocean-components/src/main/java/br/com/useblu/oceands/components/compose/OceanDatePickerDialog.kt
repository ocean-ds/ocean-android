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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanDatePickerTooltipSetup
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanDatePickerSelectionHandler
import br.com.useblu.oceands.utils.OceanIcons
import br.com.useblu.oceands.utils.isDisabled
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
    tooltipSetups: Map<String, OceanDatePickerTooltipSetup> = emptyMap(),
    onConfirm: (Date) -> Unit,
    onDismiss: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
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
                        val defaultCal = Calendar.getInstance().apply { time = defaultDate }
                        val defaultDay = CalendarDay.from(
                            defaultCal.get(Calendar.YEAR),
                            defaultCal.get(Calendar.MONTH) + 1,
                            defaultCal.get(Calendar.DAY_OF_MONTH)
                        )
                        this.selectedDate = defaultDay

                        val minDay = minDate?.let {
                            Calendar.getInstance().apply { time = it }.let { c ->
                                CalendarDay.from(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))
                            }
                        }
                        val maxDay = maxDate?.let {
                            Calendar.getInstance().apply { time = it }.let { c ->
                                CalendarDay.from(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))
                            }
                        }
                        val disabledCalendars = disabledDays.map {
                            Calendar.getInstance().apply { time = it }
                        }.toTypedArray()

                        OceanDatePickerSelectionHandler.setupCalendar(
                            context = context,
                            widget = this,
                            minDate = minDay,
                            maxDate = maxDay,
                            disabledDays = disabledCalendars
                        )

                        val selectionController = OceanDatePickerSelectionHandler(
                            tooltipSetups = tooltipSetups,
                            isDisabled = { date -> date.isDisabled(disabledDays) },
                            context = context,
                            lifecycleOwner = lifecycleOwner,
                            lastValidSelectedDate = this.selectedDate
                        )
                        setOnDateChangedListener(selectionController.getListener())
                        selectionController.showTooltipsOnOpen(this)
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
