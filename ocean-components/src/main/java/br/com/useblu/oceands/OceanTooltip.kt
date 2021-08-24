package br.com.useblu.oceands

import android.content.Context
import android.view.Gravity
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*

class OceanTooltip(
    val context: Context,
    val lifecycle: LifecycleOwner? = null
) {
    private var message: String? = null

    fun build(): Balloon {
        return createBalloon(context) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setText(message.toString())
            setTextSize(14f)
            setTextIsHtml(true)
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
            setLifecycleOwner(lifecycle)
        }
    }

    fun withMessage(message: String): OceanTooltip {
        this.message = message
        return this
    }
}