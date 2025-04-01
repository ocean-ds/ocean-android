package br.com.useblu.oceands.components.compose.blubalance.model

import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanBalanceItemAction {
    data class Button(
        val title: String
    ) : OceanBalanceItemAction

    data class Badges(
        val icons: List<OceanIcons>,
        val traillingIcon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID
    ) : OceanBalanceItemAction
}
