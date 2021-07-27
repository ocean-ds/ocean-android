package br.com.useblu.oceands.core

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R


@BindingAdapter("app:ocean_title", "app:ocean_subtitle", "app:ocean_disabled")
fun setTitleAndSubtitle(
    radioButton: AppCompatRadioButton,
    title: String?,
    subtitle: String?,
    disabled: Boolean
) {

    val darkUp = ContextCompat.getColor(
        radioButton.context,
        R.color.ocean_color_interface_dark_up
    )

    val primaryDown = ContextCompat.getColor(
        radioButton.context,
        R.color.ocean_color_brand_primary_down
    )

    val html = if (disabled) {
        if (subtitle.isNullOrEmpty()) {
            "<b><font color='$darkUp'>$title</font></b>"
        } else {
            "<b><font color='$darkUp'>$title</font></b><br/><font color='$darkUp'>$subtitle</font>"
        }
    } else {
        if (subtitle.isNullOrEmpty()) {
            "<b><font color='$primaryDown'>$title</b></font>"
        } else {
            "<b><font color='$primaryDown'>$title</b></font><br/><font color='$darkUp'>$subtitle</font>"
        }
    }

    radioButton.text = html.parseAsHtml()
}

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("app:ocean_animation_blocked", "app:ocean_icon", "app:ocean_options_card_size")
fun setAnimationBlocked(
    view: AppCompatRadioButton,
    disabled: Boolean,
    icon: Drawable,
    size: OptionsCardSize
) {
    view.isEnabled = true

    if (disabled) {
        when (size) {
            OptionsCardSize.SM -> {
                view.background = ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ocean_options_card_background_sm_disabled
                )
            }
            OptionsCardSize.MD -> {
                view.background = ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ocean_options_card_background_md_disabled
                )
            }
        }


        DrawableCompat.setTint(
            DrawableCompat.wrap(icon),
            ContextCompat.getColor(view.context, R.color.ocean_color_interface_dark_down)
        )
    }

    view.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_DOWN && disabled) {
            val animShake = AnimationUtils.loadAnimation(view.context, R.anim.shake)
            view.startAnimation(animShake)
            view.isEnabled = disabled
        }
        false
    }
}

enum class OptionsCardSize {
    SM, MD
}