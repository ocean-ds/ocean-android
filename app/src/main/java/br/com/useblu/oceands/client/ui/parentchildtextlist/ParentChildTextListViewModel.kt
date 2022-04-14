package br.com.useblu.oceands.client.ui.parentchildtextlist

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.core.OceanChildTextItem
import br.com.useblu.oceands.core.OceanParentTextItem

class ParentChildTextListViewModel : ViewModel() {

    val parent = MutableLiveData<OceanParentTextItem>()

//    private val _clickedItem: MutableLiveData<Int> = MutableLiveData()
//    val clickedItem: LiveData<Int> get() = _clickedItem
//
//    private val _longClickPressed: MutableLiveData<Boolean> = MutableLiveData()
//    val longClickPressed: LiveData<Boolean> get() = _longClickPressed

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

    fun click(position: Int) {
        Log.e("teste", "teste de click $position")
    }

    fun clickEditButton(position: Int) {
        Log.e("teste", "Teste click botão Editar" )
    }

    fun clickDeleteButton(position: Int) {
        Log.e("teste", "Teste click botão Excluir" )
    }

    fun longClick(position: Int) {
        Log.e("teste", "Teste click botão Excluir" )
//        _longClickPressed.postValue(true)
    }


}