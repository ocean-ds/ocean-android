package br.com.useblu.oceands.core

import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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
    selectionMode: MutableLiveData<Pair<Boolean, Int>>?
) {
    val checkbox = layout.findViewById<View>(R.id.checkbox)
    if (hasCheckbox == true) {
        selectionMode?.observe(layout.context as LifecycleOwner) {
            enterOrLeaveSelectionMode(checkbox, it, layout, index)
        }
    }
    setOnClickListeners(layout, checkbox, hasCheckbox, selectionMode, index, click)
}

private fun setOnClickListeners(
    layout: LinearLayoutCompat,
    checkbox: View,
    hasCheckbox: Boolean?,
    selectionMode: MutableLiveData<Pair<Boolean, Int>>?,
    index: Int?,
    click: ((Int) -> Unit)?
) {
    layout.setOnClickListener {
        oceanCheckboxBinding(checkbox)?.let {
            it.checked = it.checked?.not() ?: true
        }
    }
    oceanCheckboxBinding(checkbox)?.let {
        it.click = {
            oceanCheckboxBinding(checkbox)?.let { binding ->
                binding.checked = it
            }
            if (hasCheckbox == true) {
                if (!alreadyOnSelectionMode(checkbox)) {
                    selectionMode?.postValue(true to (index ?: -1))
                }
            }

            click?.invoke(index ?: -1)
        }
    }
}

private fun enterOrLeaveSelectionMode(
    checkbox: View,
    it: Pair<Boolean, Int>,
    layout: LinearLayoutCompat,
    index: Int?
) {
    val alreadyOnSelectionMode = alreadyOnSelectionMode(checkbox)
    if (it.first) {
        if (alreadyOnSelectionMode) return

        showCheckboxWithAnimation(layout, checkbox, checkboxChecked = it.second == index)
    } else {
        if (alreadyOnSelectionMode.not()) return

        hideCheckboxWithAnimation(layout, checkbox)
    }
}

private fun alreadyOnSelectionMode(checkbox: View) =
    checkbox.visibility == View.VISIBLE


fun hideCheckboxWithAnimation(layout: LinearLayoutCompat, checkbox: View) {
    val constraintLayout = layout.findViewById<ConstraintLayout>(R.id.constraintLayout)
    val constraintSet = ConstraintSet()
    constraintSet.clone(constraintLayout)
    constraintSet.connect(
        R.id.linearLayoutLabel,
        ConstraintSet.START,
        R.id.constraintLayout,
        ConstraintSet.START
    )
    val transition = ChangeBounds()
    transition.duration = ANIMATIONS_DURATION
    TransitionManager.beginDelayedTransition(constraintLayout, transition)
    checkbox.animateFadeOut(animationDuration = ANIMATIONS_DURATION) {
        oceanCheckboxBinding(checkbox)?.checked = false
    }
    constraintSet.applyTo(constraintLayout)
}


private fun showCheckboxWithAnimation(
    layout: LinearLayoutCompat,
    checkbox: View,
    checkboxChecked: Boolean = false
) {
    val constraintLayout = layout.findViewById<ConstraintLayout>(R.id.constraintLayout)
    val constraintSet = ConstraintSet()
    constraintSet.clone(constraintLayout)
    constraintSet.connect(
        R.id.linearLayoutLabel,
        ConstraintSet.START,
        R.id.checkbox,
        ConstraintSet.END
    )
    val transition = ChangeBounds()
    transition.duration = ANIMATIONS_DURATION
    TransitionManager.beginDelayedTransition(constraintLayout, transition)
    checkbox.animateFadeIn(animationDuration = ANIMATIONS_DURATION) {
        if (checkboxChecked)
            oceanCheckboxBinding(checkbox)?.checked = true
    }
    constraintSet.applyTo(constraintLayout)
}

private fun oceanCheckboxBinding(checkbox: View) =
    DataBindingUtil.getBinding<OceanCheckboxBinding>(checkbox)

private const val ANIMATIONS_DURATION: Long = 300
