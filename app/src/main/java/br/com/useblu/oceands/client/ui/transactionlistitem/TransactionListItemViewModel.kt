package br.com.useblu.oceands.client.ui.transactionlistitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactionListItemViewModel : ViewModel() {

    val selectionMode: MutableLiveData<Boolean> = MutableLiveData()

    private val _clickedItem: MutableLiveData<Int> = MutableLiveData()
    val clickedItem: LiveData<Int> = _clickedItem

    fun loadData() {
    }

    fun click(index: Int) {
        _clickedItem.postValue(index)
    }

    fun clear() {
        selectionMode.postValue(false)
    }
}
