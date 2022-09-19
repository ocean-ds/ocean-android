package br.com.useblu.oceands.bindingadapters

import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.motion.widget.MotionLayout
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
        enterOrLeaveSelectionMode(selectionMode, layout, checkboxBinding, click, index)
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
    checkboxBinding?.click = {
        click?.invoke(index ?: -1)
    }
}

private fun enterOrLeaveSelectionMode(
    selectionMode: MutableLiveData<Boolean>?,
    layout: LinearLayoutCompat,
    checkboxBinding: OceanCheckboxBinding?,
    click: ((Int) -> Unit)?,
    index: Int?
) {
    selectionMode?.observe(layout.context as LifecycleOwner) { isEntering ->
        val motionLayout = layout.findViewById<MotionLayout>(R.id.motionLayout)
        if (isEntering) {
            motionLayout.transitionToEnd()
        } else {
            motionLayout.transitionToStart()
            checkboxBinding?.click = {}
            checkboxBinding?.checked = false
            checkboxBinding?.executePendingBindings()
            setCheckboxClick(checkboxBinding, click, index)
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