package br.com.useblu.oceands.components.compose.pinpad.currency.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.oceanFormatWithCurrency

interface OceanPinPadCurrencyErrorSetup {
    @Composable
    fun getMinErrorMessage(minValue: Long): String {
        return stringResource(
            id = R.string.pinpad_currency_message_larger,
            minValue.oceanFormatWithCurrency()
        )
    }

    @Composable
    fun getMaxErrorMessage(maxValue: Long): String {
        return stringResource(
            id = R.string.pinpad_currency_message_less,
            maxValue.oceanFormatWithCurrency()
        )
    }

    fun getHint(minValue: Long?, maxValue: Long?, input: Long): String = ""

    val emptyError: String
        @Composable get() = stringResource(id = R.string.pinpad_currency_empty_error)

    data object Default : OceanPinPadCurrencyErrorSetup
}
