package br.com.useblu.oceands.client.ui.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TabViewModel: ViewModel() {

    val counters = MutableLiveData<ArrayList<Int>>()
    val labels1 = MutableLiveData<ArrayList<String>>()
    val labels2 = MutableLiveData<ArrayList<String>>()

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> get() = _showToast

    fun loadDetails() {
        counters.postValue(arrayListOf(3, 5, 3))
        labels1.postValue(arrayListOf("Item 1", "Item 2", "Item 3"))
        labels2.postValue(arrayListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"))
    }

    fun tabSelected(position: Int) {
        labels1.value?.let { list ->
            _showToast.postValue("Tab \"${list[position]}\" has been selected")
        }
    }

    fun tabSelected2(position: Int) {
        labels2.value?.let { list ->
            _showToast.postValue("Tab \"${list[position]}\" has been selected")
        }
    }

}
