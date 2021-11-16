package br.com.useblu.oceands.client.ui.step

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StepViewViewModel: ViewModel() {

    val currentStep = MutableLiveData<Int>()
    private val _totalSteps = MutableLiveData<Int>()
    val totalSteps: LiveData<Int> get() = _totalSteps

    fun loadData() {
        _totalSteps.postValue(6)
        currentStep.postValue(0)
    }

    fun nextStep() {
        if (currentStep.value!! < _totalSteps.value!! - 1) {
            currentStep.postValue(currentStep.value!! + 1)
        }
    }

    fun previousStep() {
        if (currentStep.value!! > 0) {
            currentStep.postValue(currentStep.value!! - 1)
        }
    }

}
