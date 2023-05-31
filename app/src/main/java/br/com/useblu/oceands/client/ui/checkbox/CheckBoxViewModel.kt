package br.com.useblu.oceands.client.ui.checkbox

import androidx.lifecycle.ViewModel

class CheckBoxViewModel : ViewModel() {

    fun executeOnStatusChanged(isChecked: Boolean) {
        println("On Change State $isChecked")
    }

    fun actionClick() {
        println("Action Click")
    }
}
