package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
sealed class OceanHeaderType {
    object Primary : OceanHeaderType()
    object Secondary : OceanHeaderType()
    data class Custom(
        val backgroundColor: String,
        val textColor: String,
        val cnpjColor: String
    ) : OceanHeaderType()
}
