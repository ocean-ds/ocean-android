package br.com.useblu.oceands.client.ui.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {

    private val _error = MutableLiveData("")
    val error: LiveData<String> get() = _error

    fun clickError() {
        if (_error.value!!.isNotBlank()) {
            _error.postValue("")
        } else {
            _error.postValue("Sample Message Error")
        }
    }
}