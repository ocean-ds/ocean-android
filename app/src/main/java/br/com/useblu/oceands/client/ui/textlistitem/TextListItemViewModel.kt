package br.com.useblu.oceands.client.ui.textlistitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextListItemViewModel : ViewModel() {
    private val _touchItem: MutableLiveData<String> = MutableLiveData()
    val touchItem: LiveData<String> get() = _touchItem

    fun onTouchItem(isChecked: Boolean){
        _touchItem.postValue("Item touched")
    }
}
