package br.com.useblu.oceands.components.compose.pinpad.currency

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPad
import br.com.useblu.oceands.components.compose.pinpad.currency.models.OceanCurrencyPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.currency.models.OceanPinPadCurrencyErrorSetup

@Composable
fun OceanCurrencyPinPad(
    initialValue: Double? = null,
    minValue: Double? = null,
    maxValue: Double? = null,
    errorSetup: OceanPinPadCurrencyErrorSetup = OceanPinPadCurrencyErrorSetup.Default
) {
    OceanPinPad(
        handler = OceanCurrencyPinPadHandler(
            initialValue = initialValue,
            minValue = minValue,
            maxValue = maxValue,
            errorSetup = errorSetup
        )
    )
}

@Preview
@Composable
fun OceanCurrencyPinPadPreview() {
    OceanCurrencyPinPad(
        initialValue = 1237.45,
        minValue = 10.0,
        maxValue = 1000.0
    )
}
