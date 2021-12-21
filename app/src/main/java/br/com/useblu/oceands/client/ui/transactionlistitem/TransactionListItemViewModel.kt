package br.com.useblu.oceands.client.ui.transactionlistitem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TransactionListItemViewModel(application: Application): AndroidViewModel(application) {
    val selectionMode:MutableLiveData<Boolean> = MutableLiveData()

    private val _clickedItem: MutableLiveData<Int> = MutableLiveData()
    val clickedItem: LiveData<Int> get() = _clickedItem

    fun loadData() {

    }

    fun click(index: Int) {
        _clickedItem.postValue(index)
    }

    fun clear() {
        selectionMode.postValue(false)
    }
}
