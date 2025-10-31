package br.com.useblu.oceands.model

enum class OceanBadgeType {
    HIGHLIGHT,
    PRIMARY,
    PRIMARY_INVERTED,
    WARNING,
    DISABLED;

    companion object {
        fun fromString(value: String): OceanBadgeType? {
            return when (value.lowercase()) {
                "highlight" -> HIGHLIGHT
                "primary" -> PRIMARY
                "primaryinverted", "chiphover" -> PRIMARY_INVERTED
                "warning" -> WARNING
                "disabled" -> DISABLED
                else -> null
            }
        }
    }
}
