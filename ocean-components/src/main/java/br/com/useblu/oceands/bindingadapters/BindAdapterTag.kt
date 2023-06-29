package br.com.useblu.oceands.bindingadapters

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanTagType

@BindingAdapter("ocean_tag_type", "ocean_tag_icon")
fun setOceanTagIcon(imageView: ImageView, type: OceanTagType?, icon: Drawable?) {
    val color: Int?
    val iconDrawable: Drawable?
    when (type) {
        OceanTagType.Neutral -> {
            imageView.isVisible = false
            iconDrawable = null
            color = R.color.ocean_color_interface_dark_up
        }
        OceanTagType.Complementary -> {
            imageView.isVisible = false
            iconDrawable = null
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
            color = R.color.ocean_color_status_warning_deep
        }
        else -> {
            iconDrawable = null
            color = null
        }
    }

    if (iconDrawable != null && color != null) {
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
}

@BindingAdapter("ocean_tag_background")
fun setOceanTagBackground(layout: LinearLayout, type: OceanTagType?) {
    val textView = layout.findViewById<TextView>(R.id.title_tag)
    val textColor: Int
    val background: Int
    when (type) {
        OceanTagType.Neutral -> {
            background = R.drawable.ocean_tag_neutral_1_background
            textColor = R.color.ocean_color_interface_dark_up
        }
        OceanTagType.Complementary -> {
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
            textColor = R.color.ocean_color_status_warning_deep
        }
        else -> {
            background = R.drawable.ocean_tag_default_background
            textColor = R.color.ocean_color_interface_dark_down
        }
    }
    textView.setTextColor(ContextCompat.getColor(textView.context, textColor))
    layout.setBackgroundResource(background)
}
@BindingAdapter("ocean_tag_text_color")
fun setOceanTagTextColor(textView: TextView, type: OceanTagType?) {
    val textColor: Int
    when (type) {
        OceanTagType.Neutral -> {
            textColor = R.color.ocean_color_interface_dark_up
        }
        OceanTagType.Complementary -> {
            textColor = R.color.ocean_color_complementary_pure
        }
        OceanTagType.Negative -> {
            textColor = R.color.ocean_color_status_negative_pure
        }
        OceanTagType.Positive -> {
            textColor = R.color.ocean_color_status_positive_deep
        }
        OceanTagType.Warning -> {
            textColor = R.color.ocean_color_status_warning_deep
        }
        else -> {
            textColor = R.color.ocean_color_interface_dark_down
        }
    }
    textView.setTextColor(ContextCompat.getColor(textView.context, textColor))
}
@BindingAdapter("ocean_tag_background_type")
fun setOceanTagBackgroundType(layout: LinearLayout, type: OceanTagType?) {
    val background: Int

    when (type) {
        OceanTagType.Neutral -> {
            background = R.drawable.ocean_tag_neutral_1_background
        }
        OceanTagType.Complementary -> {
            background = R.drawable.ocean_tag_neutral_2_background
        }
        OceanTagType.Negative -> {
            background = R.drawable.ocean_tag_negative_background
        }
        OceanTagType.Positive -> {
            background = R.drawable.ocean_tag_positive_background
        }
        OceanTagType.Warning -> {
            background = R.drawable.ocean_tag_warning_background
        }
        else -> {
            background = R.drawable.ocean_tag_default_background
        }
    }
    layout.setBackgroundResource(background)
}
