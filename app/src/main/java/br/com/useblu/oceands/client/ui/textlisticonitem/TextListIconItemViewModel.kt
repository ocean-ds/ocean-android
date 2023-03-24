package br.com.useblu.oceands.client.ui.textlisticonitem

import androidx.lifecycle.ViewModel

class TextListIconItemViewModel : ViewModel() {

    fun click() {
        println("Click Icon")
    }

    fun onLongClick() {
        println("Long Click Icon")
    }
}
