package br.com.useblu.oceands.client.ui.buttons

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonsViewModel : ViewModel() {

    private val _buttonType = MutableLiveData<String>()
    val buttonType: LiveData<String> get() = _buttonType

    private val _buttonSize = MutableLiveData<String>()
    val buttonSize: LiveData<String> get() = _buttonSize

    private val _buttonState = MutableLiveData<String>()
    val buttonState: LiveData<String> get() = _buttonState

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
        Log.d("TEST_LUIS", "${buttonType.value}${buttonSize.value}")
        Log.d("TEST_LUIS", "${_buttonType.value}${_buttonSize.value}")
    }

    fun setButtonSize(name: String) {
        _buttonSize.value = name
        Log.d("TEST_LUIS", "${buttonType.value}${buttonSize.value}")
        Log.d("TEST_LUIS", "${_buttonType.value}${_buttonSize.value}")
    }

    fun setWidthStateBlocked(isBlocked: Boolean) {
        _isWidthStateBlocked.value = isBlocked
    }

    fun setState(value: String) {
        _buttonState.value = value
    }

}