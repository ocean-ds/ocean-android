package br.com.useblu.oceands.model

enum class OceanChipItemState {
    ACTIVE_HOVER, INACTIVE_HOVER, DISABLED_ACTIVE, DISABLED_INACTIVE, DEFAULT;

    fun isActive() = this == ACTIVE_HOVER || this == DEFAULT || this == DISABLED_ACTIVE
}