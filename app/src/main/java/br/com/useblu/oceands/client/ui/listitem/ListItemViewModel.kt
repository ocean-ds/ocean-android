package br.com.useblu.oceands.client.ui.listitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListItemViewModel : ViewModel() {
    private val _clickedLink: MutableLiveData<String> = MutableLiveData()
    val clickedLink: LiveData<String> get() = _clickedLink

    fun clickedLink(id: String) {
        _clickedLink.postValue(id)
    }
}
