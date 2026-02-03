package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
enum class OceanHeaderType {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    WARNING,
    CRITICAL
}
