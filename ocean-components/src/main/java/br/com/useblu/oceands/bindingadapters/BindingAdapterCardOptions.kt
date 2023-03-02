package br.com.useblu.oceands.bindingadapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("ocean_animation_blocked")
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

@BindingAdapter("colorTagRecommend")
fun setColor(view: LinearLayout, color: String?) {
    val unwrappedDrawable =
        ContextCompat.getDrawable(view.context, R.drawable.ocean_tag_recommend_default)
    unwrappedDrawable?.let { drawable ->
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        color?.let { color ->
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(color))
            view.background = wrappedDrawable
        }
    }
}
