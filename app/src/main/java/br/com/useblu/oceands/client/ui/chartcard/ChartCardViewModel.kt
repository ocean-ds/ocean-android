package br.com.useblu.oceands.client.ui.chartcard

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel

class ChartCardViewModel : ViewModel() {
    var items = listOf(
        OceanChartItem(
            title = "Item 1",
            valueFormatted = "R$ 25",
            value = 25.0f,
            color = Color(0xFF0025E0),
            subtitle = "Subtitle 1",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        OceanChartItem(
            title = "Item 2",
            valueFormatted = "R$ 75",
            value = 75.0f,
            color = Color(0xFFE02020),
            subtitle = "Subtitle 2",
            information = "Isto equivale a 75 reais"
        ),
        OceanChartItem(
            title = "Item 3",
            valueFormatted = "R$ 75",
            value = 75.0f,
            color = Color(0xFFFF8800),
            subtitle = "Subtitle 3",
            information = "Isto equivale a 75 reais"
        ),
        OceanChartItem(
            title = "Item 4",
            valueFormatted = "70",
            value = 70.0f,
            color = Color(0xFF757575),
            subtitle = "Subtitle 4",
            information = "Isto equivale a 70 reais"
        ),
        OceanChartItem(
            title = "Item 5",
            valueFormatted = "60",
            value = 60.0f,
            color = Color(0xFF4CAF50),
            subtitle = "Subtitle 5",
            information = "60 reaissss"
        )
    )

    val model = OceanChartModel(
        title = "Title",
        label = "Label",
        items = items,
        onItemSelected = ::onSelectItem,
        onNothingSelected = ::reset
    )

    private val _oceanDonutLiveData = MutableLiveData<OceanChartModel>()
    val oceanDonutLiveData: LiveData<OceanChartModel> = _oceanDonutLiveData

    init {
        _oceanDonutLiveData.postValue(model)
    }

    private fun onSelectItem(indexSelected: Int) {
        items = items.mapIndexed { index, it ->
            it.copy(selected = index == indexSelected)
        }

        _oceanDonutLiveData.postValue(
            model.copy(
                items = items
            )
        )
    }

    private fun reset() {
        items = items.map {
            it.copy(selected = true)
        }

        _oceanDonutLiveData.postValue(
            model.copy(
                items = items
            )
        )
    }

    fun click() {
        println("Click")
    }
}
