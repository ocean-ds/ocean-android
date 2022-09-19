package br.com.useblu.oceands.bindingadapters

import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.utils.toOceanColor

@BindingAdapter("app:initialColor", "app:finalColor", "app:opacityColor")
fun setBackground(
    view: ConstraintLayout,
    initialColor: String?,
    finalColor: String?,
    opacityColor: Int?
) {

    val init = ContextCompat.getColor(
        view.context,
        initialColor?.toOceanColor() ?: R.color.ocean_color_brand_primary_pure
    )
    val final = ContextCompat.getColor(
        view.context,
        finalColor?.toOceanColor() ?: R.color.ocean_color_brand_primary_pure
    )

    val gradient = GradientDrawable()
    gradient.colors = intArrayOf(
        init, final
    )
    gradient.gradientType = GradientDrawable.LINEAR_GRADIENT
    gradient.shape = GradientDrawable.RECTANGLE
    gradient.orientation = GradientDrawable.Orientation.LEFT_RIGHT
    gradient.alpha = opacityColor ?: 255

    view.background = gradient
}
