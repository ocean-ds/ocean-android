package br.com.useblu.oceands.core

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@BindingAdapter("app:ocean_tag_type", "app:ocean_tag_icon")
fun setOceanTagIcon(imageView: ImageView, type: OceanTagType, icon: Drawable?) {
    val color: Int
    val iconDrawable: Drawable?
    when (type) {
        OceanTagType.Neutral1 -> {
            iconDrawable = icon ?: ContextCompat.getDrawable(
                imageView.context,
                R.drawable.icon_placeholder_solid
            )
            color = R.color.ocean_color_interface_dark_up
        }
        OceanTagType.Neutral2 -> {
            iconDrawable = icon ?: ContextCompat.getDrawable(
                imageView.context,
                R.drawable.icon_placeholder_solid
            )
            color = R.color.ocean_color_complementary_pure
        }
        OceanTagType.Negative -> {
            iconDrawable = icon ?: ContextCompat.getDrawable(
                imageView.context,
                R.drawable.icon_x_circle_solid
            )
            color = R.color.ocean_color_status_negative_pure
        }
        OceanTagType.Positive -> {
            iconDrawable = icon ?: ContextCompat.getDrawable(
                imageView.context,
                R.drawable.icon_check_circle_solid
            )
            color = R.color.ocean_color_status_positive_deep
        }
        OceanTagType.Warning -> {
            iconDrawable = icon ?: ContextCompat.getDrawable(
                imageView.context,
                R.drawable.icon_exclamation_circle_solid
            )
            color = R.color.ocean_color_status_neutral_deep
        }
    }
    imageView.setImageDrawable(iconDrawable)
    ImageViewCompat.setImageTintList(
        imageView,
        ColorStateList.valueOf(
            ContextCompat.getColor(
                imageView.context,
                color
            )
        )
    )
}

@BindingAdapter("app:ocean_tag_background")
fun setOceanTagBackground(layout: LinearLayout, type: OceanTagType) {
    val textView = layout.findViewById<TextView>(R.id.title)
    val textColor: Int
    val background: Int
    when (type) {
        OceanTagType.Neutral1 -> {
            background = R.drawable.ocean_tag_neutral_1_background
            textColor = R.color.ocean_color_interface_dark_up
        }
        OceanTagType.Neutral2 -> {
            background = R.drawable.ocean_tag_neutral_2_background
            textColor = R.color.ocean_color_complementary_pure
        }
        OceanTagType.Negative -> {
            background = R.drawable.ocean_tag_negative_background
            textColor = R.color.ocean_color_status_negative_pure
        }
        OceanTagType.Positive -> {
            background = R.drawable.ocean_tag_positive_background
            textColor = R.color.ocean_color_status_positive_deep
        }
        OceanTagType.Warning -> {
            background = R.drawable.ocean_tag_warning_background
            textColor = R.color.ocean_color_status_neutral_deep
        }
    }
    textView.setTextColor(ContextCompat.getColor(textView.context, textColor))
    layout.setBackgroundResource(background)
}