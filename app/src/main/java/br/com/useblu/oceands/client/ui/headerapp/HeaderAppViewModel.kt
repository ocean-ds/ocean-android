package br.com.useblu.oceands.client.ui.headerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBalanceItemAction
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemInteraction
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemModel
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemType
import br.com.useblu.oceands.model.compose.OceanHeaderAppAction
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.utils.OceanIcons

class HeaderAppViewModel : ViewModel() {

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
            items = listOf(
                OceanBluBalanceItemModel(
                    type = OceanBluBalanceItemType.Main(
                        title = "Primeiro Label",
                        value = "R$ -35,63"
                    ),
                    interaction = OceanBluBalanceItemInteraction.Expandable(
                        items = listOf(
                            "Segundo Label" to "R$ 10,00",
                            "Terceiro Label" to "R$ 50,00"
                        )
                    )
                ),
                OceanBluBalanceItemModel(
                    type = OceanBluBalanceItemType.Text(
                        text = "Confira tudo o que entrou e saiu da sua Conta Digital Blu"
                    ),
                    interaction = OceanBluBalanceItemInteraction.Action(
                        type = OceanBalanceItemAction.Button(
                            title = "Extrato"
                        ),
                        action = { println("Click botão extrato") }
                    )
                )
            ),
            appActions = listOf(
                OceanHeaderAppAction(
                    key = "bell_example",
                    icon = OceanIcons.BELL_OUTLINE,
                    badgeCount = 2,
                    action = { println("action key: $it") }
                ),
                OceanHeaderAppAction(
                    key = "chat_example",
                    icon = OceanIcons.CHAT_ALT_THREE_OUTLINE,
                    badgeCount = 0,
                    action = { println("action key: $it") }
                )
            ),
            toggleHeaderCollapse = {
                isHeaderCollapsed = !isHeaderCollapsed
                reloadData()
            }
        )
    )
    val headerAppModel: LiveData<OceanHeaderAppModel> = _headerAppModel

    private fun reloadData() {
        val newValue = _headerAppModel.value!!.copy(
            hideBalance = isHidingBalance,
            isLoading = isLoading,
            isHeaderCollapsed = isHeaderCollapsed
        )

        _headerAppModel.postValue(newValue)
    }

    fun onClickToggleScroll() {
        isHeaderCollapsed = !isHeaderCollapsed
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
}
