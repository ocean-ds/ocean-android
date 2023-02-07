package br.com.useblu.oceands.client.ui.statuslistitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusListItemViewModel : ViewModel() {
    private val _touchItem: MutableLiveData<String> = MutableLiveData()
    val touchItem: LiveData<String> get() = _touchItem

    fun onTouchItem() {
        _touchItem.postValue("Item touched")
    }
}
