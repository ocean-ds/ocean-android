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
    val checkbox = layout.findViewById<View>(R.id.checkbox)
    layout.setOnClickListener {
        if (hasCheckbox == true) {
            if (!alreadyOnSelectionMode(checkbox)) {
                selectionMode?.postValue(true)
            }
            oceanCheckboxBinding(checkbox)?.let {
                it.checked = it.checked?.not() ?: true
            }
        } else {
            click?.invoke(index ?: -1)
        }
    }
    oceanCheckboxBinding(checkbox)?.let {
        it.click = {
            oceanCheckboxBinding(checkbox)?.let { binding ->
                binding.checked = it
            }

            click?.invoke(index ?: -1)
        }
    }
}

private fun alreadyOnSelectionMode(checkbox: View) =
    checkbox.visibility == View.VISIBLE

private fun oceanCheckboxBinding(checkbox: View) =
    DataBindingUtil.getBinding<OceanCheckboxBinding>(checkbox)
