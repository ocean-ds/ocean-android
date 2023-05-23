package br.com.useblu.oceands.model

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
    val isContentHidden: Boolean = false,
    val isHeaderCollapsed: Boolean = false,
    val currentPage: Int = 0,
    val isLoading: Boolean = false,
    val hideBalance: Boolean = false
)
