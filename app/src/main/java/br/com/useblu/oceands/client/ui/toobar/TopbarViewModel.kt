package br.com.useblu.oceands.client.ui.toobar

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopbarViewModel : ViewModel() {

    private val _shadowState: MutableLiveData<Boolean> = MutableLiveData(false)
    val shadowState: LiveData<Boolean> get() = _shadowState

    fun clickBack() {
        println("Icon back clicked!")
    }

    fun showShadow(view: View) {
        _shadowState.postValue(shadowState.value?.not())
    }

}