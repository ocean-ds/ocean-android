package br.com.useblu.oceands.client.ui.optionscard

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanOptionCardItem

class OptionsCardViewModel(application: Application) : AndroidViewModel(application) {

    val fakeOptions = listOf(
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = ContextCompat.getDrawable(
                getApplication<Application>(), R.drawable.icon_generic_primary
            ),
            title = "Title 1",
            subTitle = "Subtitle 1",
            disabled = false,
            recommend = false,
        ),
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = ContextCompat.getDrawable(
                getApplication<Application>(), R.drawable.icon_generic_primary
            ),
            title = "Title 2",
            subTitle = "Subtitle 2",
            disabled = true
        ),
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = ContextCompat.getDrawable(
                getApplication<Application>(), R.drawable.icon_generic_primary
            ),
            title = "Title 2",
            subTitle = "Subtitle 2",
            recommend = true,
            recommendColor = ContextCompat.getColor(
                getApplication(), R.color.ocean_color_status_neutral_deep
            ),
            recommendDescription = "Recomendado",
        ),
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = ContextCompat.getDrawable(
                getApplication<Application>(), R.drawable.icon_generic_primary
            ),
            title = "Title 2",
            subTitle = "Subtitle 2",
            recommend = true,
            recommendColor = ContextCompat.getColor(
                getApplication(), R.color.ocean_color_status_positive_pure
            ),
            recommendDescription = "Aproveite o cashback",
        ),
    )

    fun itemSelectClick(optionSelected: OceanOptionCardItem) {
        val data = optionSelected.data as SampleData
        println("Item Clicked >> ${data}")
    }
}

data class SampleData(
    val name: String,
    val subName: String
)