package br.com.useblu.oceands.client.ui.buttons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonsViewModel : ViewModel() {

    /**
     * Primary.small.unblocked
     * Primary.small.blocked
     * Primary.medium.unblocked
     * Primary.medium.unblocked
     *
     */

    fun setButtonType(name: String) {
        _buttonType.value = name
    }

    private val _buttonType = MutableLiveData<String>()
    val buttonType: LiveData<String> get() = _buttonType



}