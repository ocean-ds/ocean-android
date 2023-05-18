package br.com.useblu.oceands.client.ui.headerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanBalanceBluModel
import br.com.useblu.oceands.model.OceanBalanceOthersModel
import br.com.useblu.oceands.model.OceanHeaderAppModel

class HeaderAppViewModel: ViewModel() {

    private val _headerAppModel = MutableLiveData<OceanHeaderAppModel>()
    val headerAppModel: LiveData<OceanHeaderAppModel> = _headerAppModel

    private var isContentHidden = false

    fun loadData() {
        _headerAppModel.postValue(
            OceanHeaderAppModel(
                isHeaderCollapsed = false,
                clientName = "Fcr Colchões",
                formattedCnpj = "32.677.554/0001-14",
                badgeCount = 0,
                bluPlusValue = 100,
                onClickBluPlus = {},
                onClickMenu = {},
                onClickTitle = {},
                balanceBluModel = OceanBalanceBluModel(
                    firstLabel = "Saldo total na Blu",
                    firstValue = "R$ 0,00",
                    secondLabel = "Saldo atual",
                    secondValue = "R$ 0.000.000,00",
                    thirdLabel = "Agenda",
                    thirdValue = "R$ 0.000.000,00",
                    isContentHidden = isContentHidden,
                    onClickHideIcon = {
                        isContentHidden = !isContentHidden
                        loadData()
                    },
                    buttonDescription = "Confira tudo tudo tudo tudo tudo tudo tudo tudo tudo o que entrou e saiu da sua Conta Digital Blu",
                    buttonCta = "Extrato",
                    onClickButton = {}
                ),
                balanceOthersModel = OceanBalanceOthersModel(
                    firstLabel = "Saldo em Outras maquininhas",
                    firstValue = "R$ 0,00",
                    isContentHidden = isContentHidden,
                    onClickHideIcon = {
                        isContentHidden = !isContentHidden
                        loadData()
                    },
                    buttonDescription = "Lorem ipsum dolor sit amet consectetur elementum",
                    buttonCta = "Usar saldo",
                    onClickButton = {}
                )
            )
        )
    }

    fun onClickToggle() {
        _headerAppModel.value = _headerAppModel.value!!.copy(
            isHeaderCollapsed = !_headerAppModel.value!!.isHeaderCollapsed
        )
    }
}