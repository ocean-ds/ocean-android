package br.com.useblu.oceands.client.ui.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {

    private val _error = MutableLiveData("")
    val error: LiveData<String> get() = _error

    private val _items = MutableLiveData(listOf("Selecione uma opção","Blu", "Red", "Green", "Yellow"))
    val items: LiveData<List<String>> get() = _items

    val itemSelect = MutableLiveData<Int>()

    val selectItem
        get() = itemSelect.value?.let {
            items.value?.get(it)
        }

    private val _items2 = MutableLiveData(listOf("Selecione uma opção", "Blu", "Red", "Green", "Yellow"))
    val items2: LiveData<List<String>> get() = _items2

    val itemSelect2 = MutableLiveData<Int>()

    val selectItem2
        get() = itemSelect2.value?.let {
            items2.value?.get(it)
        }

    fun clickError() {
        if (_error.value!!.isNotBlank()) {
            _error.postValue("")
        } else {
            _error.postValue("Sample Message Error")
        }
    }
}