package br.com.useblu.oceands.client.ui.buttons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonsViewModel : ViewModel() {

    private val _buttonType = MutableLiveData<String>()
    val buttonType: LiveData<String> get() = _buttonType

    private val _isWidthStateBlocked = MutableLiveData<Boolean>()
    val isWidthStateBlocked: LiveData<Boolean> get() = _isWidthStateBlocked

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

    fun setWidthStateBlocked(isBlocked: Boolean) {
        _isWidthStateBlocked.value = isBlocked
    }

}