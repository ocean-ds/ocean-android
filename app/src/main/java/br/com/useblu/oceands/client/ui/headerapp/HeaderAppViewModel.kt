package br.com.useblu.oceands.client.ui.headerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanBalanceBluModel
import br.com.useblu.oceands.model.OceanBalanceOthersModel
import br.com.useblu.oceands.model.OceanHeaderAppModel

class HeaderAppViewModel: ViewModel() {

    private var isBalanceValueHidden = false
    private var othersBalanceActive = false
    private var isHeaderCollapsed = false
    private var isLoading = false
    private var isHidingBalance = false

    private val _headerAppModel = MutableLiveData(
        OceanHeaderAppModel(
            isHeaderCollapsed = isHeaderCollapsed,
            isLoading = isLoading,
            clientName = "Fcr Colchões",
            formattedCnpj = "32.677.554/0001-14",
            bluPlusValue = 100,
            balanceBluModel = OceanBalanceBluModel(
                firstLabel = "Saldo total na Blu",
                firstValue = "R$ 0,00",
                secondLabel = "Saldo atual",
                secondValue = "R$ 0.000.000,00",
                thirdLabel = "Agenda",
                thirdValue = "R$ 0.000.000,00",
                onClickHideIcon = {
                    isBalanceValueHidden = !isBalanceValueHidden
                    reloadData()
                },
                buttonDescription = "Confira o que entrou e saiu da sua conta",
                buttonCta = "Extrato",
                onClickButton = {
                    println("Click botão saldo Blu")
                },
                onClickExpandScroll = {
                    isHeaderCollapsed = false
                    reloadData()
                }
            ),
            balanceOthersModel = OceanBalanceOthersModel(
                title = "Saldo em Outras maquininhas",
                description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
                buttonCta = "Trazer saldo para a Blu",
                buttonCtaCollapsed = "Trazer saldo",
                onClickButton = {
                    println("Click botão outras maquininhas")
                }
            )
        )
    )
    val headerAppModel: LiveData<OceanHeaderAppModel> = _headerAppModel

    private fun reloadData() {
        val newValue = _headerAppModel.value!!.copy(
            hideBalance = isHidingBalance,
            isLoading = isLoading,
            isHeaderCollapsed = isHeaderCollapsed,
            isContentHidden = isBalanceValueHidden,
            balanceOthersModel = OceanBalanceOthersModel(
                title = "Saldo em Outras maquininhas",
                description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
                buttonCta = "Trazer saldo para a Blu",
                buttonCtaCollapsed = "Trazer saldo",
                onClickButton = {
                    println("Click botão outras maquininhas")
                }
            )
        )

        _headerAppModel.postValue(newValue)
    }

    fun onClickToggleScroll() {
        isHeaderCollapsed = !isHeaderCollapsed
        reloadData()
    }

    fun onClickPortabilidade() {
        othersBalanceActive = !othersBalanceActive
        reloadData()
    }

    fun onClickToggleLoading() {
        isLoading = !isLoading
        reloadData()
    }

    fun onClickToggleHideBalance() {
        isHidingBalance = !isHidingBalance
        reloadData()
    }

    fun onPageChange(newPage: Int) {
        _headerAppModel.value = _headerAppModel.value!!.copy(
            currentPage = newPage
        )
    }
}