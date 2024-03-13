package br.com.useblu.oceands.client.ui.descriptorlistitem

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanDescriptorListItem
import br.com.useblu.oceands.utils.OceanIcons

class DescriptorListViewModel : ViewModel() {
    val entries = listOf(
        OceanDescriptorListItem(
            title = "Desconto",
            value = "R$ 45,00",
            color = "colorStatusPositiveDeep",
            icon = OceanIcons.TAG_SOLID.token
        ),
        OceanDescriptorListItem(
            title = "Cobrança",
            value = "R$ 42,00",
            newValue = "Zero",
            color = "colorStatusPositiveDeep",
        ),
        OceanDescriptorListItem(
            title = "Taxa",
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
            icon = OceanIcons.TAG_SOLID.token
        ),
        OceanDescriptorListItem(
            title = "Valor do pagamento",
            value = "R$ 40,00",
            color = "colorInterfaceDarkPure",
            isBold = true
        )
    )
}
