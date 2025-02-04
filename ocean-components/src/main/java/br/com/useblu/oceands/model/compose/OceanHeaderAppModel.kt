package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Immutable

@Immutable
data class OceanHeaderAppModel(
    val clientName: String = "",
    val formattedCnpj: String = "",
    val onClickTitle: () -> Unit = {},
    val bluPlusValue: Int = 0,
    val onClickBluPlus: () -> Unit = {},
    val onClickMenu: () -> Unit = {},
    val badgeCount: Int = 0,
    val balanceBluModel: OceanBalanceBluModel = OceanBalanceBluModel(),
    val balanceOthersModel: OceanBalanceOthersModel = OceanBalanceOthersModel(),
    val isHeaderCollapsed: Boolean = false,
    val hideBalance: Boolean = false,
    val hideBluPlus: Boolean = true,
    val isLoading: Boolean = false,
    val appActions: List<OceanHeaderAppAction<Any>> = emptyList()
)