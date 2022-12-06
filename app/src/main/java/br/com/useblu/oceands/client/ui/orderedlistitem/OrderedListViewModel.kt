package br.com.useblu.oceands.client.ui.orderedlistitem

import androidx.lifecycle.ViewModel

class OrderedListViewModel : ViewModel() {

    val entries = listOf(
        "Quando aprovar uma cobrança Pagblu e confirmar os dados, basta clicar em “Usar saldo de outras maquininhas”.",
        "Pronto! É só aguardar que você será avisado quando o pagamento for concluído."
    )
}
