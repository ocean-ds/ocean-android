package br.com.useblu.oceands.model

import androidx.compose.runtime.Stable

@Stable
enum class OceanChipItemState {
    HOVER_ACTIVE, HOVER_INACTIVE, DISABLED_ACTIVE, DISABLED_INACTIVE, DEFAULT_ACTIVE, DEFAULT_INACTIVE;

    fun isActive() = this == HOVER_ACTIVE || this == DEFAULT_ACTIVE || this == DISABLED_ACTIVE
    fun isClickable() = this != DISABLED_ACTIVE && this != DISABLED_INACTIVE
}