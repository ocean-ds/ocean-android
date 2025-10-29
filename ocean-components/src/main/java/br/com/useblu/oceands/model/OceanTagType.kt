package br.com.useblu.oceands.model

import androidx.compose.runtime.Composable
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

@Composable
fun fromString(tagType: String): OceanTagType {
    return when (tagType) {
        "Neutral" -> OceanTagType.Neutral
        "NeutralPrimary" -> OceanTagType.NeutralPrimary
        "Negative" -> OceanTagType.Negative
        "Positive" -> OceanTagType.Positive
        "Warning" -> OceanTagType.Warning
        "Complementary" -> OceanTagType.Complementary
        "Important" -> OceanTagType.Important
        "Highlight" -> OceanTagType.Highlight
        else -> OceanTagType.Neutral
    }
}
