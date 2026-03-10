package br.com.useblu.oceands.client.ui.datepicker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.client.ui.home.textAction
import br.com.useblu.oceands.components.OceanDatePickerFullscreen
import br.com.useblu.oceands.components.OceanToast
import br.com.useblu.oceands.components.compose.OceanDatePickerDialog
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.OceanDatePickerTooltipSetup
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

            val tooltipSetups = remember {
                val today = Calendar.getInstance()
                val tomorrow = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
                mapOf(
                    today.toDayOfYearKey() to OceanDatePickerTooltipSetup(
                        message = "Data selecionada (hoje)",
                        autoDismissMs = 3000L,
                        showOnOpen = true
                    ),
                    tomorrow.toDayOfYearKey() to OceanDatePickerTooltipSetup(
                        message = "Amanhã - sem auto dismiss",
                        autoDismissMs = null
                    ),
                    Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 15) }
                        .toDayOfYearKey() to OceanDatePickerTooltipSetup(
                        message = "Dia 15 - tooltip customizada"
                    ),
                    Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 20) }
                        .toDayOfYearKey() to OceanDatePickerTooltipSetup(
                        message = "Dia 20 - tooltip customizada"
                    )
                )
            }

            OceanTheme {
                Scaffold { paddingValues ->
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        textAction(
                            text = "DatePicker Fullscreen",
                            onClick = { onDatePickerFullscreen(tooltipSetups) }
                        )
                        textAction(
                            text = "DatePicker Compose Dialog",
                            onClick = { showComposeDialog = true }
                        )
                    }
                }

                if (showComposeDialog) {
                    val calendarMinDate = remember {
                        Calendar.getInstance().apply {
                            time = Date()
                        }
                    }
                    val calendarMaxDate = remember {
                        Calendar.getInstance().apply {
                            time = Date()
                            add(Calendar.MONTH, 5)
                        }
                    }
                    val disabledDays = remember {
                        listOf(
                            Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 15) }.time,
                            Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 20) }.time
                        )
                    }
                    OceanDatePickerDialog(
                        title = "",
                        infoTitle = "Date Picker Compose",
                        infoMessage = "Selecione uma data. Tooltips aparecem em datas configuradas (hoje e amanhã).",
                        minDate = calendarMinDate.time,
                        maxDate = calendarMaxDate.time,
                        defaultDate = calendarMinDate.time,
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

    private fun onDatePickerFullscreen(tooltipSetups: Map<String, OceanDatePickerTooltipSetup>) {
        val calendarMinDate = Calendar.getInstance().apply {
            time = Date()
            add(Calendar.DAY_OF_YEAR, 1)
        }
        val calendarMaxDate = Calendar.getInstance().apply {
            time = Date()
            add(Calendar.MONTH, 5)
        }
        val disabledDays = arrayOf(
            Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 15) },
            Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 20) }
        )

        OceanDatePickerFullscreen(supportFragmentManager)
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
}
