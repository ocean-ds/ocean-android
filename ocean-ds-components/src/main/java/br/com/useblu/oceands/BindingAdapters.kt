package br.com.useblu.oceands

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:ocean_visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}


