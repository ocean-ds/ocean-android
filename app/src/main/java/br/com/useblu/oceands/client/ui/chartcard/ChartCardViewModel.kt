package br.com.useblu.oceands.client.ui.chartcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.model.OceanDonutItem
import br.com.useblu.oceands.model.OceanDonutModel

class ChartCardViewModel : ViewModel() {
    var items = listOf(
        OceanDonutItem(
            title = "Item 1",
            valueFormatted = "R$ 25",
            value = 25.0f,
            color = R.color.colorPrimary,
            subtitle = "Subtitle 1",
            information = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            percent = "10%"
        ),
        OceanDonutItem(
            title = "Item 2",
            valueFormatted = "R$ 75",
            value = 75.0f,
            color = R.color.ocean_color_status_negative_pure,
            subtitle = "Subtitle 2",
            information = "Isto equivale a 75 reais",
            percent = "20%"
        ),
        OceanDonutItem(
            title = "Item 3",
            valueFormatted = "R$ 75",
            value = 75.0f,
            color = R.color.ocean_color_status_warning_deep,
            subtitle = "Subtitle 3",
            information = "Isto equivale a 75 reais",
            percent = "30%"
        ),
        OceanDonutItem(
            title = "Item 4",
            valueFormatted = "70",
            value = 70.0f,
            color = R.color.colorAccent,
            subtitle = "Subtitle 4",
            information = "Isto equivale a 70 reais",
            percent = "40%"
        ),
        OceanDonutItem(
            title = "Item 5",
            valueFormatted = "60",
            value = 60.0f,
            color = R.color.ocean_color_brand_primary_pure,
            subtitle = "Subtitle 5",
            information = "60 reaissss",
            percent = "50%"
        )
    )

    val model = OceanDonutModel(
        title = "Title",
        label = "Label",
        items = items,
        onItemSelected = ::onSelectItem,
        onNothingSelected = ::reset
    )

    private val _oceanDonutLiveData = MutableLiveData<OceanDonutModel>()
    val oceanDonutLiveData: LiveData<OceanDonutModel> = _oceanDonutLiveData

    init {
        _oceanDonutLiveData.postValue(model)
    }

    private fun onSelectItem(itemSelected: OceanDonutItem) {
        items = items.map {
            it.copy(selected = it == itemSelected)
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

