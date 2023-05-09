package br.com.useblu.oceands.client.ui.donut

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel

class DonutViewModel : ViewModel() {

    private val _selectedItem = MutableLiveData<OceanDonutItem?>()
    val selectedItem: LiveData<OceanDonutItem?> = _selectedItem

    val donutModel = OceanDonutModel(
        title = "0",
        label = "Label",
        items = listOf(
            OceanDonutItem(
                label = "Item 1",
                formattedValue = "R$ 25,00",
                value = 25.0f,
                color = R.color.colorPrimary
            ),
            OceanDonutItem(
                label = "Item 2",
                formattedValue = "R$ 75,00",
                value = 75.0f,
                color = R.color.colorPrimaryDark
            ),
            OceanDonutItem(
                label = "Item 3",
                formattedValue = "R$ 75,00",
                value = 75.0f,
                color = R.color.cardview_shadow_start_color
            ),
            OceanDonutItem(
                label = "Item 4",
                formattedValue = "R$ 70,00",
                value = 70.0f,
                color = R.color.colorAccent
            ),
            OceanDonutItem(
                label = "Item 5",
                formattedValue = "R$ 60,00",
                value = 60.0f,
                color = R.color.ocean_color_brand_primary_pure
            ),
        ),
        onItemSelected = {
            _selectedItem.postValue(it)
        },
        onNothingSelected = {
            _selectedItem.postValue(null)
        }
    )
}
