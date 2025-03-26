package br.com.useblu.oceands.client.ui.informativecard

import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanInfoListItem

class InformativeCardViewModel : ViewModel() {

    val fistInfo = OceanInfoListItem(
        label = "First Info",
        value = "R$ 100,00",
        information = "First Information"
    )

    val secondInfo = OceanInfoListItem(
        label = "Second Info",
        value = "R$ 200,00",
        information = "Second Information"
    )

    fun click() {
        println("Click")
    }
}
