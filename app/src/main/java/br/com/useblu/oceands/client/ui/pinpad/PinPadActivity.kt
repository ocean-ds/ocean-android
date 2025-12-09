package br.com.useblu.oceands.client.ui.pinpad

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.blupos.presentation.features.sale.installments.pinpad.OceanInstallmentsPinPadHandler
import br.com.useblu.oceands.components.OceanToast
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPad
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.handlers.currency.OceanCurrencyPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.handlers.installments.models.OceanInstallmentsPinPadTextSetup
import br.com.useblu.oceands.components.compose.pinpad.handlers.password.OceanPasswordPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.handlers.password.models.OceanPasswordPinPadType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

class PinPadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PinPadExample(toast = OceanToast(this)) }
    }
}

@Preview
@Composable
private fun PinPadExample(toast: OceanToast) {
    var selectedType: PinPadKnownType by remember { mutableStateOf(PinPadKnownType.Currency) }
    var handler: OceanPinPadHandler<*>? by remember { mutableStateOf(null) }
    var isEnabled: Boolean by remember { mutableStateOf(true) }
    var isLoading: Boolean by remember { mutableStateOf(false) }

    OceanTheme {
        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    when (selectedType) {
                        PinPadKnownType.Currency -> {
                            val currencyHandler = OceanCurrencyPinPadHandler(
                                minValue = 1.0,
                                maxValue = 100000.0,
                                testValue = 0.03
                            )
                            handler = currencyHandler
                            OceanPinPad(
                                handler = currencyHandler,
                                isEnabled = isEnabled,
                                isLoading = isLoading
                            )
                        }

                        PinPadKnownType.Password -> {
                            val passwordHandler = OceanPasswordPinPadHandler(
                                type = OceanPasswordPinPadType.FixedSize(6),
                                inputColor = OceanColors.interfaceDarkDeep
                            )
                            handler = passwordHandler
                            OceanPinPad(
                                handler = passwordHandler,
                                isEnabled = isEnabled,
                                isLoading = isLoading
                            )
                        }

                        PinPadKnownType.Installments -> {
                            val installmentsHandler = OceanInstallmentsPinPadHandler(
                                maxInstallments = 12,
                                textSetup = object : OceanInstallmentsPinPadTextSetup {
                                    override fun getPlaceholder(): String = "0x"

                                    override fun getHint(maxInstallments: Int): String =
                                        "Até ${maxInstallments}x"

                                    override fun getErrorEmpty(): String = "Selecione o número de parcelas"

                                    override fun getErrorMax(maxInstallments: Int): String =
                                        "O número máximo de parcelas é $maxInstallments"
                                }
                            )
                            handler = installmentsHandler
                            OceanPinPad(
                                handler = installmentsHandler,
                                isEnabled = isEnabled,
                                isLoading = isLoading
                            )
                        }
                    }
                }
            },
            bottomBar = {
                Column {
                    OceanButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(OceanSpacing.xs),
                        text = "Continuar",
                        buttonStyle = OceanButtonStyle.PrimaryMedium,
                        onClick = {
                            toast.withMessage("result: ${handler?.getResult()}")
                                .show()
                        }
                    )

                    OceanDivider()

                    Row(
                        modifier = Modifier
                            .padding(OceanSpacing.xs),
                        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
                    ) {
                        OceanButton(
                            modifier = Modifier
                                .weight(1f),
                            text = "Toggle Enabled",
                            buttonStyle = OceanButtonStyle.PrimarySmall,
                            onClick = { isEnabled = isEnabled.not() }
                        )

                        OceanButton(
                            modifier = Modifier
                                .weight(1f),
                            text = "Toggle Loading",
                            buttonStyle = OceanButtonStyle.PrimarySmall,
                            onClick = { isLoading = isLoading.not() }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(OceanSpacing.xs),
                        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
                    ) {
                        PinPadKnownType.entries.forEach {
                            OceanButton(
                                modifier = Modifier
                                    .weight(1f),
                                text = it.name,
                                buttonStyle = OceanButtonStyle.PrimarySmall,
                                onClick = { selectedType = it }
                            )
                        }
                    }
                    OceanSpacing.StackLG()
                }
            }
        )
    }
}

private enum class PinPadKnownType {
    Currency,
    Password,

    Installments
}
