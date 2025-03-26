package br.com.useblu.oceands.model

import androidx.compose.runtime.Immutable

@Immutable
enum class OceanTagType {
    Neutral,
    NeutralPrimary,
    Negative,
    Positive,
    Warning,
    Complementary,
    Important,
    Highlight
}
