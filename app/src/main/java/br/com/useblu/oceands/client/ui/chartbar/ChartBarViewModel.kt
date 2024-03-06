package br.com.useblu.oceands.client.ui.chartbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel

class ChartBarViewModel : ViewModel() {
    var items = listOf(
        OceanChartItem(
            title = "22/02",
            value = 5.0f,
            color = R.color.colorPrimary
        ),
        OceanChartItem(
            title = "23/02",
            value = 4.0f,
            color = R.color.ocean_color_status_negative_pure
        ),
        OceanChartItem(
            title = "24/02",
            value = 10.0f,
            color = R.color.ocean_color_status_warning_deep,
        ),
        OceanChartItem(
            title = "25/02",
            value = 7.0f,
            color = R.color.colorAccent
        ),
        OceanChartItem(
            title = "26/02",
            value = 6.0f,
            color = R.color.ocean_color_brand_primary_pure
        )
    )

    val model = OceanChartModel(
        items = items,
        onItemSelected = ::onSelectItem,
        onNothingSelected = ::reset
    )

    private val _chartLiveData = MutableLiveData<OceanChartModel>()
    val chartLiveData: LiveData<OceanChartModel> = _chartLiveData

    init {
        _chartLiveData.postValue(model)
    }

    private fun onSelectItem(selectedIndex: Int) {
        items = items.mapIndexed { index, it ->
            it.copy(selected = index == selectedIndex)
        }

        _chartLiveData.postValue(
            model.copy(
                items = items
            )
        )
    }

    private fun reset() {
        items = items.map {
            it.copy(selected = true)
        }

        _chartLiveData.postValue(
            model.copy(
                items = items
            )
        )
    }

    fun click() {
        println("Click")
    }
}

