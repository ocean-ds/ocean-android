package br.com.useblu.oceands.client.ui.chartbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.chart.OceanChartBarItem
import br.com.useblu.oceands.model.chart.OceanChartBarModel

class ChartBarViewModel : ViewModel() {
    var items = listOf(
        OceanChartBarItem(
            label = "21/02",
            value = 5.0f
        ),
        OceanChartBarItem(
            label = "22/02",
            value = 7.0f
        ),
        OceanChartBarItem(
            label = "23/02",
            value = 4.0f
        ),
        OceanChartBarItem(
            label = "24/02",
            value = 5.0f
        ),
        OceanChartBarItem(
            label = "25/02",
            value = 7.0f
        ),
        OceanChartBarItem(
            label = "26/02",
            value = 6.0f
        )
    )

    val model = OceanChartBarModel(
        items = items
    )

    private val _chartLiveData = MutableLiveData(model)
    val chartLiveData: LiveData<OceanChartBarModel> = _chartLiveData

    fun click() {
        println("Click")
    }
}
