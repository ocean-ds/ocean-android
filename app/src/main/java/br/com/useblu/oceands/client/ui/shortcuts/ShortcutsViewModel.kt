package br.com.useblu.oceands.client.ui.shortcuts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanShortcutItem
import br.com.useblu.oceands.model.OceanShortcutLayoutMode

class ShortcutsViewModel(application: Application) : AndroidViewModel(application) {

    private val _itemsTinyVertical = MutableLiveData<List<OceanShortcutItem>>()
    val itemsTinyVertical: LiveData<List<OceanShortcutItem>> = _itemsTinyVertical

    private val _itemsTinyHorizontal = MutableLiveData<List<OceanShortcutItem>>()
    val itemsTinyHorizontal: LiveData<List<OceanShortcutItem>> = _itemsTinyHorizontal

    private val _itemsSmall = MutableLiveData<List<OceanShortcutItem>>()
    val itemsSmall: LiveData<List<OceanShortcutItem>> = _itemsSmall

    private val _itemsMedium = MutableLiveData<List<OceanShortcutItem>>()
    val itemsMedium: LiveData<List<OceanShortcutItem>> = _itemsMedium

    fun loadData() {
        _itemsTinyVertical.postValue(getItemsTinyVertical())
        _itemsTinyHorizontal.postValue(getItemsTinyHorizontal())
        _itemsSmall.postValue(getItemsSmall())
        _itemsMedium.postValue(getItemsMedium())
    }

    private fun getItemsSmall() = listOf(
        OceanShortcutItem(
            icon = "pixoutline",
            label = "Shortcut 1",
            subTitle = "Lorem ipsum dolor sit amet, consectetur.",
            badgeType = OceanBadgeType.HIGHLIGHT,
            blocked = true,
            action = {
                println("Shortcut 1 clicked")
            }
        ),
        OceanShortcutItem(
            icon = "linkoutline",
            label = "Shortcut 2",
            subTitle = "Lorem ipsum dolor sit amet, consectetur.",
            count = "99+",
            badgeType = OceanBadgeType.PRIMARY,
            action = {
                println("Shortcut 2 clicked")
            }
        ),
        OceanShortcutItem(
            icon = "barcodeoutline",
            label = "Shortcut 3 with big label",
            subTitle = "Lorem ipsum dolor sit amet, consectetur.",
            count = "3",
            badgeType = OceanBadgeType.DISABLED,
            action = {
                println("Shortcut 3 clicked")
            }
        ),
        OceanShortcutItem(
            icon = "switchhorizontaloutline",
            label = "Shortcut 4",
            subTitle = "Lorem ipsum dolor sit amet, consectetur.",
            count = "1",
            badgeType = OceanBadgeType.WARNING,
            action = {
                println("Shortcut 4 clicked")
            }
        ),
        OceanShortcutItem(
            icon = "pagbluoutline",
            label = "Shortcut 5",
            subTitle = "Lorem ipsum dolor sit amet, consectetur.",
            count = "1",
            badgeType = OceanBadgeType.PRIMARY_INVERTED,
            action = {
                println("Shortcut 5 clicked")
            }
        )
    ).map {
        it.copy(layoutMode = OceanShortcutLayoutMode.Vertical)
    }

    private fun getItemsTinyVertical() = getItemsSmall().map {
        it.copy(
            layoutMode = OceanShortcutLayoutMode.Vertical,
            subTitle = ""
        )
    }

    private fun getItemsTinyHorizontal() = getItemsSmall().map {
        it.copy(
            layoutMode = OceanShortcutLayoutMode.Horizontal,
            subTitle = ""
        )
    }

    private fun getItemsMedium() = getItemsSmall().map {
        it.copy(
            layoutMode = OceanShortcutLayoutMode.Vertical
        )
    }
}