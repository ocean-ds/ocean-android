package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemModel

@Immutable
data class OceanHeaderAppModel(
    val clientName: String = "",
    val formattedCnpj: String = "",
    val onClickTitle: () -> Unit = {},
    val bluPlusValue: Int = 0,
    val onClickBluPlus: () -> Unit = {},
    val onClickMenu: () -> Unit = {},
    val badgeCount: Int = 0,
    val items: List<OceanBalanceItemModel> = emptyList(),
    val variant: OceanHeaderVariant = OceanHeaderVariant.PRIMARY,
    val showBalanceToggle: Boolean = true,
    val isHeaderCollapsed: Boolean = false,
    val toggleHeaderCollapse: () -> Unit = {},
    val hideBalance: Boolean = false,
    val hideBluPlus: Boolean = true,
    val isLoading: Boolean = false,
    val appActions: List<OceanHeaderAppAction<Any>> = emptyList()
)

@Immutable
enum class OceanHeaderVariant {
    PRIMARY,
    SECONDARY
}
