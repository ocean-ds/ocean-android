package br.com.useblu.oceands.components.compose.balance.model

sealed interface OceanBalanceItemInteraction {
    val showExpandedInfo: Boolean
        get() = false
    val action: () -> Unit
    val canClickFullItem: Boolean

    data class Expandable(
        val bluBalanceItems: List<Pair<String, String>>,
        val acquirersBalanceItems: List<Pair<String, String>>? = null,
        val badges: List<String> = emptyList(),
        val wrapSize: Int = 1,
        val lockedTitle: String = "",
        val lockedItems: List<Pair<String, String>> = emptyList(),
        val banner: OceanBalanceBanner? = null,
        override val showExpandedInfo: Boolean = false
    ) : OceanBalanceItemInteraction {
        override val action: () -> Unit = { }
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
