package br.com.useblu.oceands.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.OceanTooltip

@BindingAdapter("showTooltip")
fun showTooltip(imageView: ImageView, text: String?) {
    text?.let {
        imageView.setOnClickListener {

            val tooltip = OceanTooltip(
                context = imageView.context,
            ).withMessage(text).build()

            tooltip.showAlignTop(imageView)
            tooltip.dismissWithDelay(5000)
        }
    }
}