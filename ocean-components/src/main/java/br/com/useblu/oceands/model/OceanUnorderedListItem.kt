package br.com.useblu.oceands.model

data class OceanUnorderedListItem(
    val title: String,
    val icon: String,
    val needsRoundBackgroundIcon: Boolean,
    val needTrailingSpacer: Boolean = false,
    val needLeadingSpacer: Boolean = false,
)
