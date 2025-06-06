package br.com.useblu.oceands.client.ui.transactionfooter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanInlineTextList

class TransactionFooterViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    val entries = listOf(
        OceanInlineTextList(
            label = "Label0",
            value = "R$ 40,00",
            color = "colorStatusPositiveDeep",
            icon = "https://portal-cicloentrada.blu.com.br/assets/icons/tag-solid-1454da330c6ed8c065fc231a92595a77de1ff697e40ce64de53ce4f9603d0e27.png"
        ),
        OceanInlineTextList(
            label = "Label1",
            value = "R$ 40,00",
            newValue = "Zero",
            color = "colorStatusPositiveDeep"
        ),
        OceanInlineTextList(
            label = "Label2",
            value = "R$ 10,00",
            color = "colorStatusPositiveDeep"
        ),
        OceanInlineTextList(
            label = "Label3",
            value = "Zero",
            color = "colorStatusPositiveDeep"
        ),
        OceanInlineTextList(
            label = "Label4",
            value = "R$ 40,00",
            color = "colorInterfaceDarkDown"
        ),
        OceanInlineTextList(
            label = "Label5",
            value = "Calculada no dia",
            color = "colorStatusNeutralDeep"
        ),
        OceanInlineTextList(
            label = "Label6",
            value = "R$ 10,00",
            tooltip = "tooltip",
            color = "colorStatusPositiveDeep",
            icon = "https://portal-cicloentrada.blu.com.br/assets/icons/tag-solid-1454da330c6ed8c065fc231a92595a77de1ff697e40ce64de53ce4f9603d0e27.png"
        ),
        OceanInlineTextList(
            label = "Label7",
            value = "Não Disponível",
            tooltip = "tooltip",
            color = "colorInterfaceDarkUp"
        ),
        OceanInlineTextList(
            label = "Label8",
            value = "R$ 40,00",
            color = "colorInterfaceDarkPure",
            isBold = true
        )
    )

    fun click() {
        val msg = "Button Clicked"
        _message.postValue(msg)
    }
}
