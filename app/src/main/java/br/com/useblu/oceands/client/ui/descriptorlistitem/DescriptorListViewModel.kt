package br.com.useblu.oceands.client.ui.descriptorlistitem

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanDescriptorListItem

class DescriptorListViewModel : ViewModel() {
    val entries = listOf(
        OceanDescriptorListItem(
            title = "Desconto",
            value = "R$ 40,00",
            color = "colorStatusPositiveDeep",
            icon = "https://portal-cicloentrada.blu.com.br/assets/icons/tag-solid-1454da330c6ed8c065fc231a92595a77de1ff697e40ce64de53ce4f9603d0e27.png"
        ),
        OceanDescriptorListItem(
            title = "Taxa de Antecipação",
            value = "R$ 40,00",
            newValue = "Zero",
            color = "colorStatusPositiveDeep",
        ),
        OceanDescriptorListItem(
            title = "Taxa de Antecipação",
            value = "R$ 10,00",
            color = "colorStatusPositiveDeep",
        ),
        OceanDescriptorListItem(
            title = "Taxa de Antecipação",
            value = "Zero",
            color = "colorStatusPositiveDeep",
        ),
        OceanDescriptorListItem(
            title = "Desconto",
            value = "R$ 40,00",
            color = "colorInterfaceDarkPure",
        ),
        OceanDescriptorListItem(
            isDivider = true,
        ),
        OceanDescriptorListItem(
            title = "Taxa de Antecipação",
            value = "Calculada no momento do pagamento",
            color = "colorStatusNeutralDeep",
        ),
        OceanDescriptorListItem(
            title = "Taxa de Antecipação",
            value = "R$ 10,00",
            color = "colorStatusPositiveDeep",
            icon = "https://portal-cicloentrada.blu.com.br/assets/icons/tag-solid-1454da330c6ed8c065fc231a92595a77de1ff697e40ce64de53ce4f9603d0e27.png"
        ),
        OceanDescriptorListItem(
            title = "Valor do pagamento",
            value = "R$ 40,00",
            color = "colorInterfaceDarkPure",
            isBold = true,
        ),
    )
}
