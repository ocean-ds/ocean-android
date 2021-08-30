package br.com.useblu.oceands.client.ui.shortcuts

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.adapter.OceanShortcutItem
import br.com.useblu.oceands.client.R

class ShortcutsViewModel(application: Application): AndroidViewModel(application) {

    private val _items = MutableLiveData<List<OceanShortcutItem>>()
    val items: LiveData<List<OceanShortcutItem>> get() = _items

    private val _itemSelected = MutableLiveData<OceanShortcutItem>()
    val itemSelected: LiveData<OceanShortcutItem> get() = _itemSelected

    private val _items2 = MutableLiveData<List<OceanShortcutItem>>()
    val items2: LiveData<List<OceanShortcutItem>> get() = _items2

    private val _itemSelected2 = MutableLiveData<OceanShortcutItem>()
    val itemSelected2: LiveData<OceanShortcutItem> get() = _itemSelected2

    fun loadData(context: Context) {
        _items.postValue(getItems())
        _items2.postValue(getItemsHighlighted())
    }

    fun itemSelect(position: Int) {
        _items.value?.let { items ->
            _itemSelected.postValue(items[position])
        }
    }

    fun itemSelect2(position: Int) {
        _items2.value?.let { items ->
            _itemSelected2.postValue(items[position])
        }
    }

    private fun getItems() = listOf(
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic_primary), "Shortcut 1"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic_primary), "Shortcut 2"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic_primary), "Shortcut 3"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic_primary), "Shortcut 4"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic_primary), "Shortcut 5"),
    )

    private fun getItemsHighlighted() = listOf(
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic), "Shortcut 1"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic), "Shortcut 2"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic), "Shortcut 3"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic), "Shortcut 4"),
        OceanShortcutItem(ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_generic), "Shortcut 5"),
    )

}