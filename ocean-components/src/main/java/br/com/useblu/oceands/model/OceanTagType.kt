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
    Highlight;

    companion object {
        fun fromString(tagType: String): OceanTagType {
            return when (tagType.lowercase().replace("_", "")) {
                "neutral", "neutral01" -> Neutral
                "neutralprimary", "neutral02" -> NeutralPrimary
                "negative" -> Negative
                "positive" -> Positive
                "warning" -> Warning
                "complementary" -> Complementary
                "important" -> Important
                "highlight" -> Highlight
                else -> Neutral
            }
        }
    }
}
