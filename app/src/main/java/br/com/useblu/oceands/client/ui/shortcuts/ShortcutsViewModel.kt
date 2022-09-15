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
import br.com.useblu.oceands.core.OceanBadgeType

class ShortcutsViewModel(application: Application) : AndroidViewModel(application) {

    private val _items = MutableLiveData<List<OceanShortcutItem>>()
    val items: LiveData<List<OceanShortcutItem>> get() = _items

    private val _itemSelected = MutableLiveData<OceanShortcutItem>()
    val itemSelected: LiveData<OceanShortcutItem> get() = _itemSelected

    private val _items2 = MutableLiveData<List<OceanShortcutItem>>()
    val items2: LiveData<List<OceanShortcutItem>> get() = _items2

    private val _itemSelected2 = MutableLiveData<OceanShortcutItem>()
    val itemSelected2: LiveData<OceanShortcutItem> get() = _itemSelected2

    fun loadData() {
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
        OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 1",
            badgeType = OceanBadgeType.DEFAULT
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 2",
            count = "1",
            badgeType = OceanBadgeType.BRAND_DEFAULT
        ),
        OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 3",
            badgeType = OceanBadgeType.NEUTRAL
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 4",
            count = "1",
            badgeType = OceanBadgeType.ALERT
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 5",
            count = "1",
            badgeType = OceanBadgeType.COMPLEMENTARY
        ),
    )

    private fun getItemsHighlighted() = listOf(
        OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 1",
            count = "1",
            badgeType = OceanBadgeType.DEFAULT
        ),OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 2",
            count = "1",
            badgeType = OceanBadgeType.BRAND_DEFAULT
        ),OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 3",
            count = "1",
            badgeType = OceanBadgeType.NEUTRAL
        ),OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 4",
            count = "1",
            badgeType = OceanBadgeType.COMPLEMENTARY
        ),OceanShortcutItem(
            iconUrl = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            label = "Shortcut 5",
            count = "1",
            badgeType = OceanBadgeType.ALERT
        ),
    )

}