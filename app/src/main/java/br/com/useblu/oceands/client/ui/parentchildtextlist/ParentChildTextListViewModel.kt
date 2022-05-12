package br.com.useblu.oceands.client.ui.parentchildtextlist

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.core.OceanChildTextItem
import br.com.useblu.oceands.core.OceanParentTextItem

class ParentChildTextListViewModel : ViewModel() {

    val parent = MutableLiveData<OceanParentTextItem>()

    private val _showMessage: MutableLiveData<String> = MutableLiveData()
    val showMessage: LiveData<String> get() = _showMessage

    fun loadData(drawable: Drawable?) {
        parent.postValue(
            OceanParentTextItem(
                image = drawable, title = "header", subTitle = "subtitle",
                children = listOf(
                    OceanChildTextItem(
                        image = drawable,
                        title = "nome",
                        subTitle = "teste",
                        isRemove = true
                    ),
                    OceanChildTextItem(
                        image = drawable,
                        title = "nome",
                        subTitle = "teste",
                        isRemove = true,
                        isEdit = true
                    ),
                    OceanChildTextItem(
                        image = drawable,
                        title = "nome",
                        subTitle = "teste",
                        isRemove = true,
                        isEdit = true
                    )
                )
            )
        )
    }

    fun click(position: Int) {
        _showMessage.postValue("Clicou no item: $position")
    }

    fun clickEditButton(position: Int) {
        _showMessage.postValue("Clicou no botão Editar no item: $position")
    }

    fun clickDeleteButton(position: Int) {
        _showMessage.postValue("Clicou no botão Excluir no item: $position")
    }

    fun longClick(position: Int) {
        _showMessage.postValue("Clicou long press no item: $position")
    }
}