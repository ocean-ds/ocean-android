package br.com.useblu.oceands

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.createBalloon

class OceanTooltip(
    val context: Context,
    val lifecycle: LifecycleOwner?
) {
    private var message: String? = null

    fun build(): Balloon {
        return createBalloon(context) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setText(message.toString())
            setTextSize(14f)
            setArrowSize(12)
            setArrowOrientation(ArrowOrientation.BOTTOM)
            setArrowPosition(0.5f)
            setPadding(16)
            setCornerRadius(4f)
            setBalloonAnimationStyle(R.style.Fade_Balloon_Library)
            setBackgroundColorResource(R.color.ocean_color_interface_dark_deep)
            setDismissWhenClicked(false)
            setDismissWhenTouchOutside(false)
            setLifecycleOwner(lifecycleOwner)
        }
    }

    fun withMessage(message: String): OceanTooltip {
        this.message = message
        return this
    }
}