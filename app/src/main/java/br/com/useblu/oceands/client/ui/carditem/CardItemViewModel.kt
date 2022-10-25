package br.com.useblu.oceands.client.ui.carditem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardItemViewModel: ViewModel() {


    private val _shouldShowMessage = MutableLiveData<String>()
    val shouldShowMessage: LiveData<String> get() = _shouldShowMessage


    fun actionClickCardItemOne() {
        _shouldShowMessage.postValue("Click Action Card Item One")
    }

    fun actionClickCardItemTwo() {
        _shouldShowMessage.postValue("Click Action Card Item Two")
    }
}
