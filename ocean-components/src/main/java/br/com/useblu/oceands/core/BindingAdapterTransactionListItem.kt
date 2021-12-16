package br.com.useblu.oceands.core

import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.OceanCheckboxBinding

@BindingAdapter("click", "hasCheckbox", "index", "selectionMode")
fun setClickListener(
    layout: LinearLayoutCompat,
    click: ((Int) -> Unit)?,
    hasCheckbox: Boolean?,
    index: Int?,
    selectionMode: MutableLiveData<Boolean>?
) {
    layout.setOnClickListener {
        if (hasCheckbox == true) {
            selectionMode?.postValue(true)
            val view = layout.findViewById<View>(R.id.checkbox)
            DataBindingUtil.getBinding<OceanCheckboxBinding>(view).let {
                it ?: return@let
                it.checked = it.checked?.not() ?: true
            }
        }

        click?.invoke(index ?: -1)
    }
}
