package br.com.useblu.oceands.components.compose.cardbalance.model

import br.com.useblu.oceands.components.compose.OceanButtonModel

sealed interface OceanCardBalanceItemInteraction {
    val showExpandedInfo: Boolean
        get() = false
    val action: () -> Unit
    val canClickFullItem: Boolean

    data class Expandable(
        val items: List<Pair<String, String>>,
        val badges: List<String>,
        override val showExpandedInfo: Boolean = false
    ) : OceanCardBalanceItemInteraction {
        override val action: () -> Unit = { Unit }
        val hiddenValue: String = "R$ ••••••"
        override val canClickFullItem: Boolean = true
    }

    data class Action(
        val button: OceanButtonModel,
        override val canClickFullItem: Boolean
    ) : OceanCardBalanceItemInteraction {
        override val action: () -> Unit = button.onClick
    }
}
