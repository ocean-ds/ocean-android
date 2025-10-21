package br.com.useblu.oceands.components.compose.balance.model

sealed interface OceanBalanceItemInteraction {
    val showExpandedInfo: Boolean
        get() = false
    val action: () -> Unit
    val canClickFullItem: Boolean

    data class Expandable(
        val items: List<Pair<String, String>>,
        val badges: List<String> = emptyList(),
        override val showExpandedInfo: Boolean = false
    ) : OceanBalanceItemInteraction {
        override val action: () -> Unit = { Unit }
        val hiddenValue: String = "R$ ••••••"
        override val canClickFullItem: Boolean = true
    }

    data class Action(
        val type: OceanBalanceItemActionType,
        override val action: () -> Unit = {}
    ) : OceanBalanceItemInteraction {
        override val canClickFullItem: Boolean = type.canClickFullItem
    }
}
