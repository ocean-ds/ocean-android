package br.com.useblu.oceands.client.ui.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TabViewModel: ViewModel() {

    val counters = MutableLiveData<List<Int>>()
    val labels1 = MutableLiveData<List<String>>()
    val labels2 = MutableLiveData<List<String>>()

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> = _showToast

    fun loadDetails() {
        counters.postValue(listOf(2, 5, 3))
        labels1.postValue(listOf("Item 1", "Item 2"))
        labels2.postValue(
            listOf(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7"
            )
        )

        viewModelScope.launch {
            delay(2000)
            labels1.postValue(listOf("Item 11", "Item 22"))
        }
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
