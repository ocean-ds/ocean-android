package br.com.useblu.oceands.core

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("app:ocean_animation_blocked")
fun setAnimationBlocked(
    view: View,
    disabled: Boolean
) {
    view.isEnabled = true

    view.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_DOWN && disabled) {
            val animShake = AnimationUtils.loadAnimation(view.context, R.anim.shake)
            view.startAnimation(animShake)
            view.isEnabled = disabled
        }
        false
    }
}

@BindingAdapter("app:colorTagRecommend")
fun setColor(view: LinearLayout, color: Int?) {
    val unwrappedDrawable =
        ContextCompat.getDrawable(view.context, R.drawable.ocean_tag_recommend_default)
    unwrappedDrawable?.let { drawable ->
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        color?.let { color ->
            DrawableCompat.setTint(wrappedDrawable, color)
            view.background = wrappedDrawable
        }
    }
}
