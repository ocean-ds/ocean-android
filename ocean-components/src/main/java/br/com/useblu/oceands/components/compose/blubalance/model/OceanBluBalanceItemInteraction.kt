package br.com.useblu.oceands.components.compose.blubalance.model

sealed interface OceanBluBalanceItemInteraction {
    val showExpandedInfo: Boolean
        get() = false
    val action: () -> Unit

    data class Expandable(
        val items: List<Pair<String, String>>,
        override val showExpandedInfo: Boolean = false
    ) : OceanBluBalanceItemInteraction {
        override val action: () -> Unit = { Unit }
    }

    data class Action(
        val type: OceanBalanceItemAction,
        override val action: () -> Unit
    ) : OceanBluBalanceItemInteraction
}
