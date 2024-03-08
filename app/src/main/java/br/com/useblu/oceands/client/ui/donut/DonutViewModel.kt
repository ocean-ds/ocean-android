package br.com.useblu.oceands.client.ui.donut

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel

class DonutViewModel : ViewModel() {

    private val _selectedItem = MutableLiveData<OceanChartItem?>()
    val selectedItem: LiveData<OceanChartItem?> = _selectedItem

    val donutItems = listOf(
        OceanChartItem(
            title = "Item 1",
            value = 25.0f,
            valueFormatted = "R$ 25,00",
            color = R.color.colorPrimary
        ),
        OceanChartItem(
            title = "Item 2",
            valueFormatted = "R$ 75,00",
            value = 75.0f,
            color = R.color.colorPrimaryDark
        ),
        OceanChartItem(
            title = "Item 3",
            valueFormatted = "R$ 75,00",
            value = 75.0f,
            color = R.color.cardview_shadow_start_color
        ),
        OceanChartItem(
            title = "Item 4",
            valueFormatted = "R$ 70,00",
            value = 70.0f,
            color = R.color.colorAccent
        ),
        OceanChartItem(
            title = "Item 5",
            valueFormatted = "R$ 60,00",
            value = 60.0f,
            color = R.color.ocean_color_brand_primary_pure
        ),
    )

    val emptyDonutModel = OceanChartModel(
        title = "0",
        label = "Empty Donut",
        items = emptyList(),
        onItemSelected = { },
        onNothingSelected = { }
    )

    val donutModel = OceanChartModel(
        title = "0",
        label = "Label",
        items = donutItems,
        onItemSelected = {
            _selectedItem.postValue(donutItems[it])
        },
        onNothingSelected = {
            _selectedItem.postValue(null)
        }
    )
}
