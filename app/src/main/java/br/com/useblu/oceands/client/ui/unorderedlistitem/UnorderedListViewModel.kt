package br.com.useblu.oceands.client.ui.unorderedlistitem

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanUnorderedListItem

class UnorderedListViewModel : ViewModel() {

    val entries = listOf(
        OceanUnorderedListItem(
            title = "Quando aprovar uma cobrança Pagblu e confirmar os dados, basta clicar em “Usar saldo de outras maquininhas”.",
            icon = "shieldcheckoutline",
            needsRoundBackgroundIcon = false
        ),
        OceanUnorderedListItem(
            title = "Mais vantagens: maior flexibilidade para pagar boletos e transferir via Pix",
            icon = "transactionsoutline",
            needsRoundBackgroundIcon = false
        ),
        OceanUnorderedListItem(
            title = "Mais segurança: garanta que apenas o celular cadastrado aprove transações",
            icon = "lockclosedoutline",
            needsRoundBackgroundIcon = false
        )
    )
}
