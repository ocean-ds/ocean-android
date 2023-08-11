package br.com.useblu.oceands.client.ui.bottomnavigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanIconType
import br.com.useblu.oceands.model.compose.OceanBottomNavigationModel
import kotlin.random.Random

class BottomNavigationViewModel: ViewModel() {

    private val _menuItems = MutableLiveData<List<OceanBottomNavigationModel>>(emptyList())
    val menuItems: LiveData<List<OceanBottomNavigationModel>> = _menuItems

    var selectedIndex by mutableStateOf(0)
        private set

    fun clearItems() {
        _menuItems.postValue(emptyList())
        selectedIndex = 0
    }

    fun addItem() {
        _menuItems.postValue(
            _menuItems.value!!.toMutableList().apply {
                val currentSize = size
                add(
                    OceanBottomNavigationModel(
                        label = "Teste ${Random.nextInt(0, 100)}",
                        activeIcon = OceanIconType.HOME_SOLID,
                        inactiveIcon = OceanIconType.HOME_OUTLINE,
                        onClickListener = {
                            selectedIndex = currentSize
                        }
                    )
                )
            }
        )
    }

    fun loadDefaultItems() {
        val menuItems = listOf(
            OceanBottomNavigationModel(
                label = "Home",
                activeIcon = OceanIconType.HOME_SOLID,
                inactiveIcon = OceanIconType.HOME_OUTLINE,
                onClickListener = {
                    selectedIndex = 0
                }
            ),
            OceanBottomNavigationModel(
                label = "Pagar",
                activeIcon = OceanIconType.PAGBLU_SOLID,
                inactiveIcon = OceanIconType.PAGBLU_OUTLINE,
                onClickListener = {
                    selectedIndex = 1
                }
            ),
            OceanBottomNavigationModel(
                label = "Transferir",
                activeIcon = OceanIconType.SWITCH_HORIZONTAL_SOLID,
                inactiveIcon = OceanIconType.SWITCH_HORIZONTAL_OUTLINE,
                onClickListener = {
                    selectedIndex = 2
                }
            ),
            OceanBottomNavigationModel(
                label = "Relatórios",
                activeIcon = OceanIconType.REPORT_SOLID,
                inactiveIcon = OceanIconType.REPORT_OUTLINE,
                onClickListener = {
                    selectedIndex = 3
                }
            )
        )

        _menuItems.postValue(menuItems)
    }
}