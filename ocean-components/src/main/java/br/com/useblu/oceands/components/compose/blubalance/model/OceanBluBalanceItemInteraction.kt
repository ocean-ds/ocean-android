package br.com.useblu.oceands.components.compose.blubalance.model

sealed interface OceanBluBalanceItemInteraction {
    val showExpandedInfo: Boolean
        get() = false
    val action: () -> Unit
    val canClickFullItem: Boolean

    data class Expandable(
        val items: List<Pair<String, String>>,
        override val showExpandedInfo: Boolean = false
    ) : OceanBluBalanceItemInteraction {
        override val action: () -> Unit = { Unit }
        val hiddenValue: String = "R$ ••••••"
        override val canClickFullItem: Boolean = true
    }

    data class Action(
        val type: OceanBalanceItemAction,
        override val action: () -> Unit
    ) : OceanBluBalanceItemInteraction {
        override val canClickFullItem: Boolean = type.canClickFullItem
    }
}
