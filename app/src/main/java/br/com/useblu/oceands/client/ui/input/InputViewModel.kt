package br.com.useblu.oceands.client.ui.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {

    private val _error = MutableLiveData("")
    val error: LiveData<String> get() = _error

    private val _items = MutableLiveData(listOf("Blu", "Red", "Green", "Yellow"))
    val items: LiveData<List<String>> get() = _items

    val itemSelect = MutableLiveData<Int>()

    private val selectItem
        get() = itemSelect.value?.let {
            items.value?.get(it)
        }

    fun clickError() {
        if (_error.value!!.isNotBlank()) {
            _error.postValue("")
        } else {
            _error.postValue("Sample Message Error")
        }
    }
}