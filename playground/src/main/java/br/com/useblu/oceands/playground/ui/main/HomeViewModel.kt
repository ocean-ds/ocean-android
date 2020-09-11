
package br.com.useblu.oceands.playground.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _spacingDefault = MutableLiveData<Float>()
    val spacingDefault: MutableLiveData<Float> get() = _spacingDefault

    private val _spacingMedium = MutableLiveData<Float>()
    val spacingMedium: MutableLiveData<Float> get() = _spacingMedium

    private val _spacingLarge = MutableLiveData<Float>()
    val spacingLarge: MutableLiveData<Float> get() = _spacingLarge

    private val _textValue = MutableLiveData<String>()
    val textValue: MutableLiveData<String> get() = _textValue

    private val _textValueMedium = MutableLiveData<String>()
    val textValueMedium: MutableLiveData<String> get() = _textValueMedium

    private val _textValueLarge = MutableLiveData<String>()
    val textValueLarge: MutableLiveData<String> get() = _textValueLarge

    private val _showNextState = MutableLiveData<String>()
    val showNextState: MutableLiveData<String> get() = _showNextState

    init {
        _spacingDefault.value = 1.0f
        _spacingMedium.value = 1.1f
        _spacingLarge.value = 1.5f
    }
    fun onClickButton() {
        _textValue.value = "TIGHT - Soluções de negócios inovadoras e que beneficiam toda a cadeia, do varejo à indústria."
        _textValueMedium.value = "MEDIUM - Soluções de negócios inovadoras e que beneficiam toda a cadeia, do varejo à indústria."
        _textValueLarge.value = "COMFY - Soluções de negócios inovadoras e que beneficiam toda a cadeia, do varejo à indústria."
        _showNextState.value = System.currentTimeMillis().toString()
    }

    fun onNewDefault(double: Float) {
        _spacingDefault.value = double
    }

    fun onNewMedium(double: Float) {
        _spacingMedium.value = double
    }

    fun onNewLarge(double: Float) {
        _spacingLarge.value = double
    }
}
