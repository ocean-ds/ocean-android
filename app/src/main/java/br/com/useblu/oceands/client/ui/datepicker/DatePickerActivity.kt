package br.com.useblu.oceands.client.ui.datepicker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.client.ui.home.textAction
import br.com.useblu.oceands.components.OceanDatePickerFullscreen
import br.com.useblu.oceands.components.OceanToast
import br.com.useblu.oceands.components.compose.OceanDatePickerDialog
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.input.OceanTextInput
import br.com.useblu.oceands.extensions.oceanFormat
import br.com.useblu.oceands.extensions.toDate
import br.com.useblu.oceands.model.OceanDatePickerTooltipSetup
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType
import br.com.useblu.oceands.utils.datepicker.toDayOfYearKey
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
class DatePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showComposeDialog by remember { mutableStateOf(false) }

            val defaultMinDate = remember {
                Calendar.getInstance().apply { time = Date() }.time
            }
            val defaultMaxDate = remember {
                Calendar.getInstance().apply {
                    time = Date()
                    add(Calendar.MONTH, 5)
                }.time
            }
            val defaultDay15 = remember {
                Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 15) }.time
            }
            val defaultDay20 = remember {
                Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 20) }.time
            }
            val defaultTomorrow = remember {
                Calendar.getInstance().apply {
                    time = Date()
                    add(Calendar.DAY_OF_YEAR, 1)
                }.time
            }

            var navTitle by remember { mutableStateOf("Date Picker") }
            var title by remember { mutableStateOf("Date Picker") }
            var subtitle by remember {
                mutableStateOf("Selecione uma data. Tooltips aparecem em datas configuradas (hoje e amanhã).")
            }
            var minDateStr by remember { mutableStateOf(defaultMinDate.oceanFormat("dd/MM/yyyy")) }
            var maxDateStr by remember { mutableStateOf(defaultMaxDate.oceanFormat("dd/MM/yyyy")) }
            var tooltip1Date by remember { mutableStateOf(defaultMinDate.oceanFormat("dd/MM/yyyy")) }
            var tooltip1Message by remember { mutableStateOf("Data selecionada (hoje)") }
            var tooltip2Date by remember { mutableStateOf(defaultTomorrow.oceanFormat("dd/MM/yyyy")) }
            var tooltip2Message by remember { mutableStateOf("Amanhã - sem auto dismiss") }
            var tooltip3Date by remember { mutableStateOf(defaultDay15.oceanFormat("dd/MM/yyyy")) }
            var tooltip3Message by remember { mutableStateOf("Dia 15 - tooltip customizada") }
            var tooltip4Date by remember { mutableStateOf(defaultDay20.oceanFormat("dd/MM/yyyy")) }
            var tooltip4Message by remember { mutableStateOf("Dia 20 - tooltip customizada") }
            var disabled1Str by remember { mutableStateOf(defaultDay15.oceanFormat("dd/MM/yyyy")) }
            var disabled2Str by remember { mutableStateOf(defaultDay20.oceanFormat("dd/MM/yyyy")) }

            DisposableEffect(navTitle) {
                supportActionBar?.title = navTitle
                onDispose { }
            }

            OceanTheme {
                Scaffold { paddingValues ->
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        textAction(
                            text = "DatePicker Fullscreen (Deprecated)",
                            onClick = {
                                onDatePickerFullscreen(
                                    navTitle = navTitle,
                                    title = title,
                                    minDateStr = minDateStr,
                                    maxDateStr = maxDateStr,
                                    tooltip1Date = tooltip1Date,
                                    tooltip1Message = tooltip1Message,
                                    tooltip2Date = tooltip2Date,
                                    tooltip2Message = tooltip2Message,
                                    tooltip3Date = tooltip3Date,
                                    tooltip3Message = tooltip3Message,
                                    tooltip4Date = tooltip4Date,
                                    tooltip4Message = tooltip4Message,
                                    disabled1Str = disabled1Str,
                                    disabled2Str = disabled2Str
                                )
                            }
                        )
                        textAction(
                            text = "DatePicker Compose Dialog",
                            onClick = { showComposeDialog = true }
                        )
                        item {
                            ConfigSection(
                                navTitle = navTitle,
                                onNavTitleChange = { navTitle = it },
                                title = title,
                                onTitleChange = { title = it },
                                subtitle = subtitle,
                                onSubtitleChange = { subtitle = it },
                                minDateStr = minDateStr,
                                onMinDateChange = { minDateStr = it },
                                maxDateStr = maxDateStr,
                                onMaxDateChange = { maxDateStr = it },
                                tooltip1Date = tooltip1Date,
                                onTooltip1DateChange = { tooltip1Date = it },
                                tooltip1Message = tooltip1Message,
                                onTooltip1MessageChange = { tooltip1Message = it },
                                tooltip2Date = tooltip2Date,
                                onTooltip2DateChange = { tooltip2Date = it },
                                tooltip2Message = tooltip2Message,
                                onTooltip2MessageChange = { tooltip2Message = it },
                                tooltip3Date = tooltip3Date,
                                onTooltip3DateChange = { tooltip3Date = it },
                                tooltip3Message = tooltip3Message,
                                onTooltip3MessageChange = { tooltip3Message = it },
                                tooltip4Date = tooltip4Date,
                                onTooltip4DateChange = { tooltip4Date = it },
                                tooltip4Message = tooltip4Message,
                                onTooltip4MessageChange = { tooltip4Message = it },
                                disabled1Str = disabled1Str,
                                onDisabled1Change = { disabled1Str = it },
                                disabled2Str = disabled2Str,
                                onDisabled2Change = { disabled2Str = it }
                            )
                        }
                    }
                }

                if (showComposeDialog) {
                    val tooltipSetups = buildTooltipSetups(
                        tooltip1Date, tooltip1Message, true,
                        tooltip2Date, tooltip2Message, false,
                        tooltip3Date, tooltip3Message, false,
                        tooltip4Date, tooltip4Message, false
                    )
                    val minDate = minDateStr.toDate("dd/MM/yyyy") ?: defaultMinDate
                    val maxDate = maxDateStr.toDate("dd/MM/yyyy") ?: defaultMaxDate
                    val disabledDays = listOfNotNull(
                        disabled1Str.toDate("dd/MM/yyyy"),
                        disabled2Str.toDate("dd/MM/yyyy")
                    ).distinct()

                    OceanDatePickerDialog(
                        title = "Selecione uma data",
                        infoTitle = title,
                        infoMessage = subtitle,
                        minDate = minDate,
                        maxDate = maxDate,
                        defaultDate = minDate,
                        disabledDays = disabledDays,
                        tooltipSetups = tooltipSetups,
                        onConfirm = { date ->
                            OceanToast(this@DatePickerActivity)
                                .withType(OceanToast.OceanToastType.Warning)
                                .withMessage(date.toString())
                                .show()
                            showComposeDialog = false
                        },
                        onDismiss = { showComposeDialog = false }
                    )
                }
            }
        }
    }

    private fun onDatePickerFullscreen(
        navTitle: String,
        title: String,
        minDateStr: String,
        maxDateStr: String,
        tooltip1Date: String,
        tooltip1Message: String,
        tooltip2Date: String,
        tooltip2Message: String,
        tooltip3Date: String,
        tooltip3Message: String,
        tooltip4Date: String,
        tooltip4Message: String,
        disabled1Str: String,
        disabled2Str: String
    ) {
        val defaultMinDate = Calendar.getInstance().apply { time = Date() }
        val defaultMaxDate = Calendar.getInstance().apply {
            time = Date()
            add(Calendar.MONTH, 5)
        }
        val calendarMinDate = minDateStr.toDate("dd/MM/yyyy")?.let { date ->
            Calendar.getInstance().apply { time = date }
        } ?: defaultMinDate
        val calendarMaxDate = maxDateStr.toDate("dd/MM/yyyy")?.let { date ->
            Calendar.getInstance().apply { time = date }
        } ?: defaultMaxDate
        val disabledDays = listOfNotNull(
            disabled1Str.toDate("dd/MM/yyyy")?.let { Calendar.getInstance().apply { time = it } },
            disabled2Str.toDate("dd/MM/yyyy")?.let { Calendar.getInstance().apply { time = it } }
        ).distinct().toTypedArray()

        val tooltipSetups = buildTooltipSetups(
            tooltip1Date, tooltip1Message, true,
            tooltip2Date, tooltip2Message, false,
            tooltip3Date, tooltip3Message, false,
            tooltip4Date, tooltip4Message, false
        )

        OceanDatePickerFullscreen(supportFragmentManager)
            .withTitle(title)
            .withMinDate(calendarMinDate)
            .withMaxDate(calendarMaxDate)
            .withDefaultSelect(calendarMinDate)
            .withDisabledDays(disabledDays)
            .withTooltipSetups(tooltipSetups)
            .withOnConfirm { calendar ->
                OceanToast(this)
                    .withType(OceanToast.OceanToastType.Warning)
                    .withMessage(calendar.time.toString())
                    .show()
            }
            .show()
    }

    private fun buildTooltipSetups(
        date1: String,
        message1: String,
        showOnOpen1: Boolean,
        date2: String,
        message2: String,
        showOnOpen2: Boolean,
        date3: String,
        message3: String,
        showOnOpen3: Boolean,
        date4: String,
        message4: String,
        showOnOpen4: Boolean
    ): Map<String, OceanDatePickerTooltipSetup> {
        val dateFormat = "dd/MM/yyyy"
        return buildMap {
            date1.toDate(dateFormat)?.let { d ->
                if (message1.isNotBlank()) {
                    put(
                        d.toDayOfYearKey(),
                        OceanDatePickerTooltipSetup(
                            message = message1,
                            autoDismissMs = 3000L,
                            showOnTap = true,
                            showOnOpen = showOnOpen1
                        )
                    )
                }
            }
            date2.toDate(dateFormat)?.let { d ->
                if (message2.isNotBlank()) {
                    put(
                        d.toDayOfYearKey(),
                        OceanDatePickerTooltipSetup(
                            message = message2,
                            autoDismissMs = null,
                            showOnTap = true,
                            showOnOpen = showOnOpen2
                        )
                    )
                }
            }
            date3.toDate(dateFormat)?.let { d ->
                if (message3.isNotBlank()) {
                    put(
                        d.toDayOfYearKey(),
                        OceanDatePickerTooltipSetup(
                            message = message3,
                            showOnTap = true,
                            showOnOpen = showOnOpen3
                        )
                    )
                }
            }
            date4.toDate(dateFormat)?.let { d ->
                if (message4.isNotBlank()) {
                    put(
                        d.toDayOfYearKey(),
                        OceanDatePickerTooltipSetup(
                            message = message4,
                            showOnTap = true,
                            showOnOpen = showOnOpen4
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ConfigSection(
    navTitle: String,
    onNavTitleChange: (String) -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    subtitle: String,
    onSubtitleChange: (String) -> Unit,
    minDateStr: String,
    onMinDateChange: (String) -> Unit,
    maxDateStr: String,
    onMaxDateChange: (String) -> Unit,
    tooltip1Date: String,
    onTooltip1DateChange: (String) -> Unit,
    tooltip1Message: String,
    onTooltip1MessageChange: (String) -> Unit,
    tooltip2Date: String,
    onTooltip2DateChange: (String) -> Unit,
    tooltip2Message: String,
    onTooltip2MessageChange: (String) -> Unit,
    tooltip3Date: String,
    onTooltip3DateChange: (String) -> Unit,
    tooltip3Message: String,
    onTooltip3MessageChange: (String) -> Unit,
    tooltip4Date: String,
    onTooltip4DateChange: (String) -> Unit,
    tooltip4Message: String,
    onTooltip4MessageChange: (String) -> Unit,
    disabled1Str: String,
    onDisabled1Change: (String) -> Unit,
    disabled2Str: String,
    onDisabled2Change: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(OceanSpacing.xs)
    ) {
        OceanText(
            text = "Configuração",
            style = OceanTextStyle.heading4,
            color = OceanColors.interfaceDarkDeep
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = navTitle,
            label = "Navigation title",
            onTextChanged = onNavTitleChange
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = title,
            label = "Title",
            onTextChanged = onTitleChange
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = subtitle,
            label = "Subtitle",
            onTextChanged = onSubtitleChange,
            isTextArea = true
        )
        OceanSpacing.StackXS()

        OceanText(
            text = "Datas",
            style = OceanTextStyle.subtitle1,
            color = OceanColors.interfaceDarkDown
        )
        OceanSpacing.StackXXS()

        OceanTextInput(
            value = minDateStr,
            label = "Min date",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onMinDateChange
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = maxDateStr,
            label = "Max date",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onMaxDateChange
        )
        OceanSpacing.StackXS()

        OceanText(
            text = "Tooltips",
            style = OceanTextStyle.subtitle1,
            color = OceanColors.interfaceDarkDown
        )
        OceanSpacing.StackXXS()

        OceanTextInput(
            value = tooltip1Date,
            label = "Tooltip 1 date",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onTooltip1DateChange
        )
        OceanSpacing.StackXXS()
        OceanTextInput(
            value = tooltip1Message,
            label = "Tooltip 1 message",
            onTextChanged = onTooltip1MessageChange
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = tooltip2Date,
            label = "Tooltip 2 date",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onTooltip2DateChange
        )
        OceanSpacing.StackXXS()
        OceanTextInput(
            value = tooltip2Message,
            label = "Tooltip 2 message",
            onTextChanged = onTooltip2MessageChange
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = tooltip3Date,
            label = "Tooltip 3 date",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onTooltip3DateChange
        )
        OceanSpacing.StackXXS()
        OceanTextInput(
            value = tooltip3Message,
            label = "Tooltip 3 message",
            onTextChanged = onTooltip3MessageChange
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = tooltip4Date,
            label = "Tooltip 4 date",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onTooltip4DateChange
        )
        OceanSpacing.StackXXS()
        OceanTextInput(
            value = tooltip4Message,
            label = "Tooltip 4 message",
            onTextChanged = onTooltip4MessageChange
        )
        OceanSpacing.StackXS()

        OceanText(
            text = "Dias desabilitados",
            style = OceanTextStyle.subtitle1,
            color = OceanColors.interfaceDarkDown
        )
        OceanSpacing.StackXXS()

        OceanTextInput(
            value = disabled1Str,
            label = "Disabled 1",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onDisabled1Change
        )
        OceanSpacing.StackXS()

        OceanTextInput(
            value = disabled2Str,
            label = "Disabled 2",
            placeholder = "dd/MM/yyyy",
            oceanInputType = OceanInputType.Date,
            onTextChanged = onDisabled2Change
        )
    }
}
