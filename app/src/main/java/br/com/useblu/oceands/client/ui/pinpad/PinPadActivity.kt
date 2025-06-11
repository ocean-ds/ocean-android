package br.com.useblu.oceands.client.ui.pinpad

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPad
import br.com.useblu.oceands.components.compose.pinpad.handlers.currency.OceanCurrencyPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.handlers.password.OceanPasswordPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.handlers.password.models.OceanPasswordPinPadType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

class PinPadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PinPadExample() }
    }
}

@Preview
@Composable
private fun PinPadExample() {
    var selectedType: PinPadKnownType by remember { mutableStateOf(PinPadKnownType.Currency) }
    OceanTheme {
        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    when (selectedType) {
                        PinPadKnownType.Currency -> {
                            OceanPinPad(
                                handler = OceanCurrencyPinPadHandler()
                            )
                        }
                        PinPadKnownType.Password -> {
                            OceanPinPad(
                                handler = OceanPasswordPinPadHandler(
                                    type = OceanPasswordPinPadType.FixedSize(6),
                                    inputColor = OceanColors.interfaceDarkDeep
                                )
                            )
                        }
                    }
                }
            },
            bottomBar = {
                Column {
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
    Password
}
