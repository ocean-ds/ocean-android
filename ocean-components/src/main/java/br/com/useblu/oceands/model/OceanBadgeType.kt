package br.com.useblu.oceands.model

enum class OceanBadgeType {
    HIGHLIGHT,
    PRIMARY,
    PRIMARY_INVERTED,
    WARNING,
    DISABLED,
    CHIP_HOVER;

    fun fromString(value: String): OceanBadgeType? {
        return when (value.lowercase()) {
            "highlight" -> HIGHLIGHT
            "primary" -> PRIMARY
            "primaryinverted" -> PRIMARY_INVERTED
            "warning" -> WARNING
            "disabled" -> DISABLED
            "chiphover" -> CHIP_HOVER
            else -> null
        }
    }
}