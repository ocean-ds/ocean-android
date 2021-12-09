package br.com.useblu.oceands.client.ui.input

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {

    val error = MutableLiveData("")

    private val _items =
        MutableLiveData(listOf("Selecione uma opção", "Blu", "Red", "Green", "Yellow"))
    val items: LiveData<List<String>> get() = _items

    val itemSelect = MutableLiveData<Int>()

    val selectItem
        get() = itemSelect.value?.let {
            items.value?.get(it)
        }

    private val _items2 =
        MutableLiveData(
            listOf(
                "Selecione uma opção",
                "Blu",
                "Red",
                "Green",
                "Yellow",
                "Texto com o valor bem grande para testar o comportamento"
            )
        )
    val items2: LiveData<List<String>> get() = _items2

    val itemSelect2 = MutableLiveData<Int>()

    val selectItem2
        get() = itemSelect2.value?.let {
            items2.value?.get(it)
        }

    fun clickError() {
        if (error.value!!.isNotBlank()) {
            error.postValue("")
        } else {
            error.postValue("Sample Message Error")
        }
    }

    fun clickIcon() {
        Log.d("BLU", "Click no icone")
    }

    val tokenValue = MutableLiveData("")
    val tokenAutocomplete = MutableLiveData("")
    val search = MutableLiveData("")

    fun setToken() {
        tokenAutocomplete.postValue("1234")
    }
}
