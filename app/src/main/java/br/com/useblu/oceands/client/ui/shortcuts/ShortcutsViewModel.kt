package br.com.useblu.oceands.client.ui.shortcuts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.useblu.oceands.model.OceanShortcutItem
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanShortcutItemSize
import br.com.useblu.oceands.model.OceanShortcutViewMode

class ShortcutsViewModel(application: Application) : AndroidViewModel(application) {

    private val _itemsTiny = MutableLiveData<List<OceanShortcutItem>>()
    val itemsTiny: LiveData<List<OceanShortcutItem>> get() = _itemsTiny

    val itemsTinyLayoutManager = GridLayoutManager(
        application.applicationContext, 2
    )

    private val _itemsSmall = MutableLiveData<List<OceanShortcutItem>>()
    val itemsSmall: LiveData<List<OceanShortcutItem>> get() = _itemsSmall

    val itemsSmallLayoutManager = LinearLayoutManager(
        application.applicationContext,
        LinearLayoutManager.HORIZONTAL,
        false
    )

    private val _itemsMedium = MutableLiveData<List<OceanShortcutItem>>()
    val itemsMedium: LiveData<List<OceanShortcutItem>> get() = _itemsMedium

    val itemsMediumLayoutManager = GridLayoutManager(
        application.applicationContext, 2
    )

    fun loadData() {
        _itemsTiny.postValue(getItemsTiny())
        _itemsSmall.postValue(getItemsSmall())
        _itemsMedium.postValue(getItemsMedium())
    }

    private fun getItemsSmall() = listOf(
        OceanShortcutItem(
            iconUrl = "youtubesolid",
            label = "Shortcut 1",
            badgeType = OceanBadgeType.DEFAULT,
            action = {
                println("Shortcut 1 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "barcodebubblesolid",
            label = "Shortcut 2",
            subTitle = "Lorem ipsum dolor sit amet, consectetur.",
            count = "99+",
            badgeType = OceanBadgeType.BRAND_DEFAULT,
            action = {
                println("Shortcut 2 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "youtubesolid",
            label = "Shortcut 3 with big label",
            badgeType = OceanBadgeType.NEUTRAL,
            action = {
                println("Shortcut 3 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "youtubesolid",
            label = "Shortcut 4",
            count = "1",
            badgeType = OceanBadgeType.ALERT,
            action = {
                println("Shortcut 4 clicked")
            }
        ),
        OceanShortcutItem(
            iconUrl = "youtubesolid",
            label = "Shortcut 5",
            count = "1",
            badgeType = OceanBadgeType.COMPLEMENTARY,
            action = {
                println("Shortcut 5 clicked")
            }
        )
    ).map {
        it.copy(viewMode = OceanShortcutViewMode.Horizontal)
    }

    private fun getItemsTiny() = getItemsSmall().map {
        it.copy(
            size = OceanShortcutItemSize.Tiny,
            viewMode = OceanShortcutViewMode.Vertical
        )
    }

    private fun getItemsMedium() = getItemsSmall().map {
        it.copy(
            size = OceanShortcutItemSize.Medium,
            viewMode = OceanShortcutViewMode.Vertical
        )
    }

    companion object {
        const val URL = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"
    }
}