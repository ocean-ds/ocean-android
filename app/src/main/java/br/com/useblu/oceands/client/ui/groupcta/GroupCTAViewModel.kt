package br.com.useblu.oceands.client.ui.groupcta

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupCTAViewModel : ViewModel() {

    private val _actionText = MutableLiveData<String>()
    val actionText: LiveData<String> get() = _actionText

    private val _shouldShowMessage = MutableLiveData<String>()
    val shouldShowMessage: LiveData<String> get() = _shouldShowMessage

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> get() = _showProgress

    fun loadData() {
        _showProgress.postValue(false)
        _actionText.postValue("Call to action")
    }

    fun clickCTA() {
        _shouldShowMessage.postValue("Clicked on ${_actionText.value}")
    }

    fun clickLoadingView() {
        _showProgress.postValue(true)
        Handler(Looper.getMainLooper()).postDelayed({
            _showProgress.postValue(false)
        }, 3000)
    }
}
