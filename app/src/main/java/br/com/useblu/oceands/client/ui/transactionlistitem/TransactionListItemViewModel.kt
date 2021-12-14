package br.com.useblu.oceands.client.ui.transactionlistitem

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class TransactionListItemViewModel(application: Application): AndroidViewModel(application) {
    private val _hasCheckbox = MutableLiveData(false)
    val hasCheckbox: MutableLiveData<Boolean> get() = _hasCheckbox

    fun loadData() {

    }

    fun showCheckbox(view: View): Boolean {
        _hasCheckbox.postValue(true)
        return true
    }

    fun checkboxClick(checked: Boolean) {
        Toast.makeText(getApplication(), "Checked: $checked", Toast.LENGTH_SHORT).show()
    }
}
