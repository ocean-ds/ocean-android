package br.com.useblu.oceands.components.compose.blubalance.model

import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanBalanceItemAction {
    val canClickFullItem: Boolean

    data class Button(
        val title: String
    ) : OceanBalanceItemAction {
        override val canClickFullItem: Boolean = false
    }

    data class Badges(
        val acquirers: List<String>,
        val traillingIcon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID
    ) : OceanBalanceItemAction {
        override val canClickFullItem: Boolean = true
    }
}
