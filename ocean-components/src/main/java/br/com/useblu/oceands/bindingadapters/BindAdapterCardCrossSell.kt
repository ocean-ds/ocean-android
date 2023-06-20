package br.com.useblu.oceands.bindingadapters

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.utils.toOceanColor

@BindingAdapter("color")
fun setBackground(
    view: ConstraintLayout,
    color: String?,
) {

    val backgroundColor = ContextCompat.getColor(
        view.context, color?.toOceanColor() ?: R.color.ocean_color_brand_primary_pure
    )

    view.setBackgroundColor(backgroundColor)
}
