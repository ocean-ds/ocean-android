package br.com.useblu.oceands.client.ui.optionscard

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import br.com.useblu.oceands.adapter.OceanOptionCardItem
import br.com.useblu.oceands.client.R

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
                getApplication(), R.color.ocean_color_brand_primary_pure
            ),
            recommendDescription = "Recomendado",
        ),
    )

    fun itemSelectClick(optionSelected: OceanOptionCardItem) {
        println("Item 1 clicked")
    }
}