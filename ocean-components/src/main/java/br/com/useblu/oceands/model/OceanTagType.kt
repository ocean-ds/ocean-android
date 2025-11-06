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

fun fromString(tagType: String): OceanTagType {
    return when (tagType.lowercase()) {
        "neutral" -> OceanTagType.Neutral
        "neutralprimary" -> OceanTagType.NeutralPrimary
        "negative" -> OceanTagType.Negative
        "positive" -> OceanTagType.Positive
        "warning" -> OceanTagType.Warning
        "complementary" -> OceanTagType.Complementary
        "important" -> OceanTagType.Important
        "highlight" -> OceanTagType.Highlight
        else -> OceanTagType.Neutral
    }
}
