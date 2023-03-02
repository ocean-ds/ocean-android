package br.com.useblu.oceands.client.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> get() = _showProgress

    private val _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean> get() = _showToast

    fun onClickButton() {
        showProgress.let {
            _showProgress.value = true != it.value
        }

        Thread {
            Thread.sleep(1500)
            showProgress.let {
                _showProgress.postValue(true != it.value)
            }
        }.start()
    }

    fun showToast() {
        _showToast.postValue(true)

        viewModelScope.launch {
            delay(1000)
            _showToast.postValue(false)
        }
    }
}