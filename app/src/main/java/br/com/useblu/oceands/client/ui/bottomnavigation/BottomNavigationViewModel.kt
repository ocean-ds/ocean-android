package br.com.useblu.oceands.client.ui.bottomnavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanBottomNavigationMenuItem
import kotlin.random.Random

class BottomNavigationViewModel: ViewModel() {

    private val _menuItems = MutableLiveData<List<OceanBottomNavigationMenuItem>>()
    val menuItems: LiveData<List<OceanBottomNavigationMenuItem>> = _menuItems

    private val _addItem = MutableLiveData<OceanBottomNavigationMenuItem>()
    val addItem: LiveData<OceanBottomNavigationMenuItem> = _addItem

    fun clearItems() {
        _menuItems.postValue(emptyList())
    }

    fun addItem() {
        _addItem.postValue(
            OceanBottomNavigationMenuItem(
                title = "Teste ${Random.nextInt(0, 100)}",
                activeIcon = "homesolid",
                inactiveIcon = "homeoutline",
                onClickListener = {}
            )
        )
    }

    fun loadDefaultItems() {
        val menuItems = listOf(
            OceanBottomNavigationMenuItem(
                title = "Home",
                activeIcon = "homesolid",
                inactiveIcon = "homeoutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Pagar",
                activeIcon = "pagblusolid",
                inactiveIcon = "pagbluoutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Transferir",
                activeIcon = "switchhorizontalsolid",
                inactiveIcon = "switchhorizontaloutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Relatórios",
                activeIcon = "reportsolid",
                inactiveIcon = "reportoutline",
                onClickListener = {}
            )
        )

        _menuItems.postValue(menuItems)
    }
}