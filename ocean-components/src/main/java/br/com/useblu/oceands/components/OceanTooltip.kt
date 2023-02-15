package br.com.useblu.oceands.components

import android.content.Context
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import br.com.useblu.oceands.R
import com.skydoves.balloon.*

class OceanTooltip(
    val context: Context,
    val lifecycle: LifecycleOwner? = null
) {
    private var message: String? = null
    private var action: (() -> Unit)? = null

    fun build(): Balloon {
        return createBalloon(context) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setText(message.toString())
            setTextSize(14f)
            setTextIsHtml(true)
            ResourcesCompat.getFont(context, R.font.font_family_base_regular)?.let {
                setTextTypeface(it)
            }
            setTextGravity(Gravity.LEFT)
            setArrowSize(12)
            setArrowOrientation(ArrowOrientation.BOTTOM)
            setArrowPosition(0.5f)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setPadding(16)
            setCornerRadius(4f)
            setBalloonAnimationStyle(R.style.Fade_Balloon_Library)
            setBackgroundColorResource(R.color.ocean_color_interface_dark_deep)
            setDismissWhenClicked(true)
            setDismissWhenTouchOutside(true)
            setAutoDismissDuration(5000)
            setOnBalloonClickListener { action?.invoke() }
            setLifecycleOwner(lifecycle)
        }
    }

    fun withMessage(message: String): OceanTooltip {
        this.message = message
        return this
    }

    fun withClick(action: () -> Unit): OceanTooltip {
        this.action = action
        return this
    }
}