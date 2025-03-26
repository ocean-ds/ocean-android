package br.com.useblu.oceands.client.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BalanceViewModel : ViewModel() {

    private val _contentIsHidden = MutableLiveData<Boolean>()
    val contentIsHidden: LiveData<Boolean> = _contentIsHidden

    private val _headerTitle = MutableLiveData<String>()
    val headerTitle: LiveData<String> = _headerTitle

    private val _labels = MutableLiveData<List<String>>()
    val labels: LiveData<List<String>> = _labels

    private val _values = MutableLiveData<List<String>>()
    val values: LiveData<List<String>> = _values

    fun loadData() {
        _contentIsHidden.postValue(false)
        _headerTitle.postValue("Saldo na Blu ----")
        _labels.postValue(labelItems())
        _values.postValue(valueItems())
    }

    fun clickIconContentVisibility() {
        _contentIsHidden.postValue(_contentIsHidden.value?.not())
    }

    private fun labelItems() = listOf(
        "Saldo na Blu",
        "Label 2",
        "Label 3",
        "Label 4"
    )

    private fun valueItems() = listOf(
        "-1150.0",
        "50.2",
        "19.0",
        "10.0"
    )
}
