package br.com.useblu.oceands.client.ui.optionscard

import android.app.Application
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanOptionCardItem

class OptionsCardViewModel(application: Application) : AndroidViewModel(application) {

    val fakeOptions = listOf(
        OceanOptionCardItem(
            icon = ContextCompat.getDrawable(
                getApplication<Application>(), R.drawable.icon_generic_primary
            ),
            title = "Title 1",
            subTitle = "Subtitle 1",
            disabled = false,
            recommend = false,
        ),
        OceanOptionCardItem(
            icon = ContextCompat.getDrawable(
                getApplication<Application>(), R.drawable.icon_generic_primary
            ),
            title = "Title 2",
            subTitle = "Subtitle 2",
            disabled = true
        ),
        OceanOptionCardItem(
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
        println("Item 1 clicked")
    }
}