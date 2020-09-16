package br.com.useblu.oceands.client.ui.buttons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R

class ButtonsViewModel : ViewModel() {

    private val _buttonType = MutableLiveData<String>()
    val buttonType: LiveData<String> get() = _buttonType

    private val _isIconEnabled = MutableLiveData<Boolean>()
    val isIconEnabled: LiveData<Boolean> get() = _isIconEnabled

    private val _buttonSize = MutableLiveData<String>()
    val buttonSize: LiveData<String> get() = _buttonSize

    private val _buttonState = MutableLiveData<String>()
    val buttonState: LiveData<String> get() = _buttonState

    private val _isWidthStateBlocked = MutableLiveData<Boolean>()
    val isWidthStateBlocked: LiveData<Boolean> get() = _isWidthStateBlocked

    private val _backgroundColorId = MutableLiveData<Int>()
    val backgroundColorId: LiveData<Int> get() = _backgroundColorId

    fun setButtonType(name: String) {
        _buttonType.value = name

        when (name) {
            "Primary" -> {
                _backgroundColorId.value = R.color.ocean_color_interface_light_up
            }
            "Secondary" -> {
                _backgroundColorId.value = R.color.ocean_color_interface_light_deep
            }
            "Inverse" -> {
                _backgroundColorId.value = R.color.ocean_color_brand_primary_pure
            }
            else -> {
                _backgroundColorId.value = R.color.ocean_color_interface_light_up
            }
        }
    }

    fun setIconEnabled(isIconEnabled: Boolean) {
        _isIconEnabled.value = isIconEnabled
    }

    fun setButtonSize(name: String) {
        _buttonSize.value = name
    }

    fun setWidthStateBlocked(isBlocked: Boolean) {
        _isWidthStateBlocked.value = isBlocked
    }

    fun setState(value: String) {
        _buttonState.value = value
    }
}