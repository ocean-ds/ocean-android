package br.com.useblu.oceands.client.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BalanceViewModel: ViewModel() {

    private val _contentIsHidden = MutableLiveData<Boolean>()
    val contentIsHidden: LiveData<Boolean> get() = _contentIsHidden

    fun loadData() {
        _contentIsHidden.postValue(false)
    }

    fun clickIconContentVisibility() {
        _contentIsHidden.postValue(_contentIsHidden.value?.not())
    }

}