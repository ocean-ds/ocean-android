package br.com.useblu.oceands.client.ui.buttons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonsViewModel : ViewModel() {

    private val _buttonType = MutableLiveData<String>()
    val buttonType: LiveData<String> get() = _buttonType

    private val _buttonSize = MutableLiveData<String>()
    val buttonSize: LiveData<String> get() = _buttonSize

    private val _isWidthStateBlocked = MutableLiveData<Boolean>()
    val isWidthStateBlocked: LiveData<Boolean> get() = _isWidthStateBlocked

    /**
     * Primary.Small
     * Primary.Medium
     * Primary.Large
     * Secondary.Small
     * Secondary.Medium
     * Secondary.Large
     *
     */

    fun setButtonType(name: String) {
        _buttonType.value = name
    }

    fun setButtonSize(name: String) {
        _buttonSize.value = name
    }

    fun setWidthStateBlocked(isBlocked: Boolean) {
        _isWidthStateBlocked.value = isBlocked
    }

}