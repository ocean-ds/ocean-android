package br.com.useblu.oceands.model

data class OceanHeaderAppModel(
    val clientName: String,
    val formattedCnpj: String,
    val onClickTitle: () -> Unit,
    val bluPlusValue: Int,
    val onClickBluPlus: () -> Unit,
    val onClickMenu: () -> Unit,
    val badgeCount: Int,
    val balanceModel: OceanBalanceModel
)
