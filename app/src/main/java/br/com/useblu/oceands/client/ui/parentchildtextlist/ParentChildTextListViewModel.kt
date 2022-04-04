package br.com.useblu.oceands.client.ui.parentchildtextlist

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.core.OceanChildTextItem
import br.com.useblu.oceands.core.OceanParentTextItem

class ParentChildTextListViewModel : ViewModel() {

    val parent = MutableLiveData<OceanParentTextItem>()

    fun loadData(drawable: Drawable?) {
        parent.postValue(
            OceanParentTextItem(
                image = drawable, title = "header", subTitle = "subtitle",
                children = listOf(
                    OceanChildTextItem(image = drawable, title = "nome", subTitle = "teste"),
                    OceanChildTextItem(image = drawable, title = "nome", subTitle = "teste"),
                    OceanChildTextItem(image = drawable, title = "nome", subTitle = "teste")
                )

            )
        )
    }


}