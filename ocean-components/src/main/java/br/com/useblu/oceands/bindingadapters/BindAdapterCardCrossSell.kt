package br.com.useblu.oceands.bindingadapters

import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.utils.toOceanColor

@BindingAdapter("initialColor", "finalColor", "opacityColor")
fun setBackground(
    view: ConstraintLayout,
    initialColor: String?,
    finalColor: String?,
    opacityColor: Int?
) {
    val init = ContextCompat.getColor(
        view.context,
        initialColor?.toOceanColor() ?: R.color.ocean_color_interface_light_pure
    )

    val final = ContextCompat.getColor(
        view.context,
        finalColor?.toOceanColor() ?: R.color.ocean_color_interface_light_pure
    )

    val gradient = GradientDrawable()

    if (initialColor != null && finalColor != null) {
        gradient.colors = intArrayOf(
            init, final
        )
    } else if (initialColor != null) {
        gradient.colors = intArrayOf(
            init, init
        )
    }

    gradient.gradientType = GradientDrawable.LINEAR_GRADIENT
    gradient.shape = GradientDrawable.RECTANGLE
    gradient.orientation = GradientDrawable.Orientation.LEFT_RIGHT
    gradient.alpha = opacityColor ?: 255
    view.background = gradient
}
