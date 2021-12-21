package br.com.useblu.oceands.core

import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
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
    val checkboxBinding = oceanCheckboxBinding(checkbox)
    if (hasCheckbox == true) {
        layout.setOnClickListener {
            setCheckboxVisibleIfNot(checkbox)
            checkboxBinding?.checked = checkboxBinding?.checked?.not() ?: true
        }
        enterOrLeaveSelectionMode(selectionMode, layout, checkbox, checkboxBinding)
        setCheckboxClick(checkboxBinding, click, index)
    } else {
        layout.setOnClickListener {
            click?.invoke(index ?: -1)
        }
    }
}

private fun setCheckboxClick(
    checkboxBinding: OceanCheckboxBinding?,
    click: ((Int) -> Unit)?,
    index: Int?
) {
    checkboxBinding?.let { binding ->
        binding.click = {
            click?.invoke(index ?: -1)
        }
    }
}

private fun enterOrLeaveSelectionMode(
    selectionMode: MutableLiveData<Boolean>?,
    layout: LinearLayoutCompat,
    checkbox: View,
    checkboxBinding: OceanCheckboxBinding?
) {
    selectionMode?.observe(layout.context as LifecycleOwner) { isEntering ->
        if (isEntering) {
            setCheckboxVisibleIfNot(checkbox)
        } else {
            checkbox.visibility = View.GONE
            checkboxBinding?.checked = false
        }
    }
}

private fun setCheckboxVisibleIfNot(checkbox: View) {
    if (!checkboxVisible(checkbox)) checkbox.visibility = View.VISIBLE
}

private fun checkboxVisible(checkbox: View) =
    checkbox.visibility == View.VISIBLE

private fun oceanCheckboxBinding(checkbox: View) =
    DataBindingUtil.getBinding<OceanCheckboxBinding>(checkbox)
