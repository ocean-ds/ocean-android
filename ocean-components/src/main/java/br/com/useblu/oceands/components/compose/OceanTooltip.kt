package br.com.useblu.oceands.components.compose

import android.view.Gravity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder

@Composable
fun OceanTooltip(
    text: String,
    onClickBalloon: () -> Unit = {},
    tooltipAutoDismiss: Long = 5000,
    contentWrapper: @Composable () -> Unit
) {
    val builder = rememberBalloonBuilder {
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setTextGravity(Gravity.LEFT)
        setArrowSize(12)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setPaddingVertical(12)
        setPaddingHorizontal(16)
        setCornerRadius(8f)
        setBackgroundColorResource(R.color.ocean_color_interface_dark_deep)
        setDismissWhenClicked(true)
        setDismissWhenTouchOutside(true)
        setAutoDismissDuration(tooltipAutoDismiss)
        setOnBalloonClickListener { onClickBalloon.invoke() }
    }

    Balloon(
        builder = builder,
        balloonContent = {
            OceanText(
                text = text,
                fontFamily = OceanFontFamily.BaseRegular,
                color = OceanColors.interfaceLightPure
            )
        }
    ) {
        Box(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { it.showAlignTop() }
            )
        ) {
            contentWrapper()
        }
    }
}
