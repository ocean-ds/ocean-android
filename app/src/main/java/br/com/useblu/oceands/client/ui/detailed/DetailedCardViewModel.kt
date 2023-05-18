package br.com.useblu.oceands.client.ui.detailed

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanDetailedItem

class DetailedCardViewModel : ViewModel() {

    val items = listOf(
        OceanDetailedItem(
            icon = "appareloutline",
            title = "Saldo",
            info = "lorem ipsum dolor sit amet",
            value = "R$ 500,00",
            percent = 20,
            description = "Descrição do valor"
        ),
        OceanDetailedItem(
            icon = "appareloutline",
            title = "Saldo Dois",
            info = "lorem ipsum dolor sit amet",
            value = "R$ 1000,00",
            percent = 30,
            description = "Descrição do valor"
        ),
        OceanDetailedItem(
            icon = "appareloutline",
            title = "Saldo Tres",
            info = "lorem ipsum dolor sit amet",
            value = "R$ 2000,00",
            percent = 50,
            description = "Descrição do valor"
        ),
    )
}