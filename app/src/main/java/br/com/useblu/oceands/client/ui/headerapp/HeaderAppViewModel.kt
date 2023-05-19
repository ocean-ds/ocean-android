package br.com.useblu.oceands.client.ui.headerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanBalanceBluModel
import br.com.useblu.oceands.model.OceanBalanceOthersModel
import br.com.useblu.oceands.model.OceanHeaderAppModel

class HeaderAppViewModel: ViewModel() {

    private val _headerAppModel = MutableLiveData(
        OceanHeaderAppModel(
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
                buttonDescription = "Confira tudo tudo tudo tudo tudo tudo tudo tudo tudo o que entrou e saiu da sua Conta Digital Blu",
                buttonCta = "Extrato",
                onClickButton = {},
                onClickExpandScroll = {
                    isHeaderCollapsed = false
                    reloadData()
                }
            )
        )
    )
    val headerAppModel: LiveData<OceanHeaderAppModel> = _headerAppModel

    private var isBalanceValueHidden = false
    private var othersBalanceActive = true
    private var isHeaderCollapsed = false

    fun reloadData() {
        val newValue = _headerAppModel.value!!.copy(
            isHeaderCollapsed = isHeaderCollapsed,
            isContentHidden = isBalanceValueHidden,
            balanceOthersModel = OceanBalanceOthersModel(
                firstLabel = "Saldo em Outras maquininhas",
                firstValue = if (othersBalanceActive) "R$ 0,00" else null,
                onClickHideIcon = {
                    isBalanceValueHidden = !isBalanceValueHidden
                    reloadData()
                },
                buttonDescription = "Lorem ipsum dolor sit amet consectetur elementum",
                buttonCta = "Usar saldo",
                onClickButton = {}
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

    fun onPageChange(newPage: Int) {
        _headerAppModel.value = _headerAppModel.value!!.copy(
            currentPage = newPage
        )
    }
}