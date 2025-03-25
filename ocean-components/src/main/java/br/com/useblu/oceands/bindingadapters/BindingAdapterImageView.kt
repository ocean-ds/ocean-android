package br.com.useblu.oceands.bindingadapters

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.components.OceanTooltip

@BindingAdapter("showTooltip")
fun showTooltip(imageView: ImageView, text: String?) {
    text?.let {
        imageView.setOnClickListener {
            val tooltip = OceanTooltip(
                context = imageView.context,
                lifecycle = imageView.context as AppCompatActivity
            ).withMessage(text).build()

            tooltip.showAlignTop(imageView)
        }
    }
}
