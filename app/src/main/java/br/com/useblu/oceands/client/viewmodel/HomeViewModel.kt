package br.com.useblu.oceands.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> get() = _showProgress

    fun onClickButtonPrimaryMedium() {
        showProgress.let {
            _showProgress.value = true != it.value
        }

        Thread(
            Runnable {
                Thread.sleep(1500)
                showProgress.let {
                    _showProgress.postValue(true != it.value)
                }
            }
        ).start()
    }
}