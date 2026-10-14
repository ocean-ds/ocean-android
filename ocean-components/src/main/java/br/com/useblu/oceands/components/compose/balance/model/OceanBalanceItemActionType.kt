package br.com.useblu.oceands.components.compose.balance.model

import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.utils.OceanIconType
import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanBalanceItemActionType {
    val canClickFullItem: Boolean

    data class Button(
        val button: OceanButtonModel
    ) : OceanBalanceItemActionType {
        override val canClickFullItem: Boolean = false
    }

    data class ButtonSimple(
        val title: String,
        val onClick: () -> Unit = {}
    ) : OceanBalanceItemActionType {
        override val canClickFullItem: Boolean = false
    }

    data class Badges(
        val acquirers: List<String>,
        val traillingIcon: OceanIconType = OceanIcons.CHEVRON_RIGHT_SOLID
    ) : OceanBalanceItemActionType {
        override val canClickFullItem: Boolean = true
    }
}
