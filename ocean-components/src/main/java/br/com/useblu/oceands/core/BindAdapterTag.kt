package br.com.useblu.oceands.core

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@BindingAdapter("app:ocean_tag_type", "app:ocean_tag_icon")
fun setOceanTagIcon(imageView: ImageView, type: OceanTagType, icon: Drawable?) {
    val color: Int
    val defaultIcon: Drawable?
    when (type) {
        OceanTagType.Neutral1 -> {
            defaultIcon = ContextCompat.getDrawable(imageView.context, R.drawable.icon_placeholder_solid)
            color = Color.parseColor("#5872F5")
        }
        OceanTagType.Neutral2 -> {
            defaultIcon = ContextCompat.getDrawable(imageView.context, R.drawable.icon_placeholder_solid)
            color = Color.parseColor("#13BDBD")
        }
        OceanTagType.Negative -> {
            defaultIcon = ContextCompat.getDrawable(imageView.context, R.drawable.icon_x_circle_solid)
            color = ContextCompat.getColor(imageView.context, R.color.ocean_color_status_negative_pure)
        }
        OceanTagType.Positive -> {
            defaultIcon = ContextCompat.getDrawable(imageView.context, R.drawable.icon_check_circle_solid)
            color = ContextCompat.getColor(imageView.context, R.color.ocean_color_status_positive_deep)
        }
        OceanTagType.Warning -> {
            defaultIcon = ContextCompat.getDrawable(imageView.context, R.drawable.icon_exclamation_circle_solid)
            color = ContextCompat.getColor(imageView.context, R.color.ocean_color_status_neutral_deep)
        }
    }
    imageView.setImageDrawable(icon ?: defaultIcon)
    imageView.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY);
}

@BindingAdapter("app:ocean_tag_background")
fun setOceanTagBackground(layout: LinearLayout, type: OceanTagType) {
    val textView = layout.findViewById<TextView>(R.id.title)
    when (type) {
        OceanTagType.Neutral1 -> {
            layout.setBackgroundResource(R.drawable.ocean_tag_neutral_1_background)
        }
        OceanTagType.Neutral2 -> {
            layout.setBackgroundResource(R.drawable.ocean_tag_neutral_2_background)
        }
        OceanTagType.Negative -> {
            layout.setBackgroundResource(R.drawable.ocean_tag_negative_background)
            textView.setTextColor(
                ContextCompat.getColor(
                    layout.context,
                    R.color.ocean_color_status_negative_down
                )
            )
        }
        OceanTagType.Positive -> {
            layout.setBackgroundResource(R.drawable.ocean_tag_positive_background)
        }
        OceanTagType.Warning -> {
            layout.setBackgroundResource(R.drawable.ocean_tag_warning_background)
            textView.setTextColor(
                ContextCompat.getColor(
                    layout.context,
                    R.color.ocean_color_status_neutral_deep
                )
            )
        }
    }
}