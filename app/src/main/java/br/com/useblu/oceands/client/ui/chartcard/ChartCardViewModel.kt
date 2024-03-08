package br.com.useblu.oceands.client.ui.chartcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel

class ChartCardViewModel : ViewModel() {
    var items = listOf(
        OceanChartItem(
            title = "Item 1",
            valueFormatted = "R$ 25",
            value = 25.0f,
            color = R.color.colorPrimary,
            subtitle = "Subtitle 1",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "10%"
        ),
        OceanChartItem(
            title = "Item 2",
            valueFormatted = "R$ 75",
            value = 75.0f,
            color = R.color.ocean_color_status_negative_pure,
            subtitle = "Subtitle 2",
            information = "Isto equivale a 75 reais",
            percent = "20%"
        ),
        OceanChartItem(
            title = "Item 3",
            valueFormatted = "R$ 75",
            value = 75.0f,
            color = R.color.ocean_color_status_warning_deep,
            subtitle = "Subtitle 3",
            information = "Isto equivale a 75 reais",
            percent = "30%"
        ),
        OceanChartItem(
            title = "Item 4",
            valueFormatted = "70",
            value = 70.0f,
            color = R.color.colorAccent,
            subtitle = "Subtitle 4",
            information = "Isto equivale a 70 reais",
            percent = "40%"
        ),
        OceanChartItem(
            title = "Item 5",
            valueFormatted = "60",
            value = 60.0f,
            color = R.color.ocean_color_brand_primary_pure,
            subtitle = "Subtitle 5",
            information = "60 reaissss",
            percent = "50%"
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

