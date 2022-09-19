package br.com.useblu.oceands.client.ui.shortcuts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.model.OceanShortcutItem
import br.com.useblu.oceands.model.OceanBadgeType

class ShortcutsViewModel(application: Application) : AndroidViewModel(application) {

    private val _items = MutableLiveData<List<OceanShortcutItem>>()
    val items: LiveData<List<OceanShortcutItem>> get() = _items

    private val _items2 = MutableLiveData<List<OceanShortcutItem>>()
    val items2: LiveData<List<OceanShortcutItem>> get() = _items2

    fun loadData() {
        _items.postValue(getItems())
        _items2.postValue(getItemsHighlighted())
    }

    private fun getItems() = listOf(
        OceanShortcutItem(
            iconUrl = URL,
            label = "Shortcut 1",
            badgeType = OceanBadgeType.DEFAULT,
            action = {
                println("Shortcut 1 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 2",
            count = "1",
            badgeType = OceanBadgeType.BRAND_DEFAULT,
            action = {
                println("Shortcut 2 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = URL,
            label = "Shortcut 3",
            badgeType = OceanBadgeType.NEUTRAL,
            action = {
                println("Shortcut 3 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 4",
            count = "1",
            badgeType = OceanBadgeType.ALERT,
            action = {
                println("Shortcut 4 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 5",
            count = "1",
            badgeType = OceanBadgeType.COMPLEMENTARY,
            action = {
                println("Shortcut 5 clicked")
            }
        ),
    )

    private fun getItemsHighlighted() = listOf(
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 1",
            count = "1",
            badgeType = OceanBadgeType.DEFAULT,
            action = {
                println("Shortcut 1 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = URL,
            label = "Shortcut 2",
            count = "1",
            badgeType = OceanBadgeType.BRAND_DEFAULT,
            action = {
                println("Shortcut 2 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 3",
            count = "1",
            badgeType = OceanBadgeType.NEUTRAL,
            action = {
                println("Shortcut 3 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = URL,
            label = "Shortcut 4",
            count = "1",
            badgeType = OceanBadgeType.COMPLEMENTARY,
            action = {
                println("Shortcut 4 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "",
            label = "Shortcut 5",
            count = "1",
            badgeType = OceanBadgeType.ALERT,
            action = {
                println("Shortcut 5 clicked")
            }
        ),
    )

    companion object {
        const val URL = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
    }
}