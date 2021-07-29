package br.com.useblu.oceands.client.ui.optionscard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OptionsCardViewModel : ViewModel() {
    var itemSelect = MutableLiveData<Int>()

    fun itemSelectClick(){
        println("Item 1 clicked")
    }
}