package br.com.useblu.oceands.client.ui.listitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListItemViewModel : ViewModel() {
    private val _clickedLink: MutableLiveData<String> = MutableLiveData()
    val clickedLink: LiveData<String> get() = _clickedLink

    private val _longClickPressed: MutableLiveData<Boolean> = MutableLiveData()
    val longClickPressed: LiveData<Boolean> get() = _longClickPressed

    fun clickedLink(id: String) {
        _clickedLink.postValue(id)
    }

    fun longClick() {
        _longClickPressed.postValue(true)
    }
}
