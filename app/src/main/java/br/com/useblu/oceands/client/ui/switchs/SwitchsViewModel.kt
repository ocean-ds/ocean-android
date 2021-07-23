package br.com.useblu.oceands.client.ui.switchs

import androidx.lifecycle.ViewModel

class SwitchsViewModel : ViewModel() {

    fun executeOnStatusChanged(isChecked: Boolean) {
        println("On Change State $isChecked")
    }
}