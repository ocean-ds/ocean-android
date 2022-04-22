package br.com.useblu.oceands.client.ui.optionscard

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanOptionCardItem

class OptionsCardViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("ResourceType")
    val fakeOptions = listOf(
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            title = "PagBlu",
            subTitle = "Economize até 10% usando saldo futuro sem taxa de antecipação",
            disabled = false,
            recommend = false,
        ),
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            title = "PagBlu com prazo",
            subTitle = "Pague em 05/12/2021 com saldo futuro sem taxa de antecipação",
            disabled = true
        ),
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            title = "Title 2",
            subTitle = "Subtitle 2",
            recommend = true,
            recommendColor =
                getApplication<Application>().resources.getString(R.color.ocean_color_status_neutral_deep),
            recommendDescription = "Recomendado",
        ),
        OceanOptionCardItem(
            data = SampleData(
                name = "aaa",
                subName = "bbbbb"
            ),
            icon = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png",
            title = "Title 2",
            subTitle = "Subtitle 2",
            recommend = true,
            recommendColor = getApplication<Application>().resources.getString(R.color.ocean_color_status_positive_pure),
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