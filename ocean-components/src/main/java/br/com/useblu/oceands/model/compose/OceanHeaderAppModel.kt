package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemModel

@Immutable
data class OceanHeaderAppModel(
    val clientName: String = "",
    val formattedCnpj: String = "",
    val onClickTitle: () -> Unit = {},
    val bluPlusValue: Int = 0,
    val onClickBluPlus: () -> Unit = {},
    val onClickMenu: () -> Unit = {},
    val badgeCount: Int = 0,
    val items: List<OceanBluBalanceItemModel> = emptyList(),
    val isHeaderCollapsed: Boolean = false,
    val toggleHeaderCollapse: () -> Unit = {},
    val hideBalance: Boolean = false,
    val hideBluPlus: Boolean = true,
    val isLoading: Boolean = false,
    val appActions: List<OceanHeaderAppAction<Any>> = emptyList()
)
