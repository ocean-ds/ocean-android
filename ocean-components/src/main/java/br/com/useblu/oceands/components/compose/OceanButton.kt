package br.com.useblu.oceands.components.compose

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.FontFamilyBaseBold
import br.com.useblu.oceands.ui.compose.OceanButtonColors
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Preview(widthDp = 480)
@Composable
fun PreviewButton() {
    val icon = ContextCompat.getDrawable(
        LocalContext.current,
        R.drawable.icon_chevron_down
    )

    val styles = listOf(
        listOf(
            OceanButtonStyle.PrimarySmall,
            OceanButtonStyle.PrimaryMedium,
            OceanButtonStyle.PrimaryLarge,
        ),
        listOf(
            OceanButtonStyle.PrimaryInverseSmall,
            OceanButtonStyle.PrimaryInverseMedium,
            OceanButtonStyle.PrimaryInverseLarge,
        ),
        listOf(
            OceanButtonStyle.PrimaryCriticalSmall,
            OceanButtonStyle.PrimaryCriticalMedium,
            OceanButtonStyle.PrimaryCriticalLarge,
        ),
        listOf(
            OceanButtonStyle.SecondarySmall,
            OceanButtonStyle.SecondaryMedium,
            OceanButtonStyle.SecondaryLarge,
        ),
        listOf(
            OceanButtonStyle.SecondaryCriticalSmall,
            OceanButtonStyle.SecondaryCriticalMedium,
            OceanButtonStyle.SecondaryCriticalLarge,
        )
    )

    Column {
        OceanSpacing.StackXS()

        styles.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OceanSpacing.StackXS()

                it.forEach { style ->
                    OceanButton(
                        text = "Avançar",
                        showProgress = false,
                        icon = icon,
                        disabled = false,
                        modifier = Modifier,
                        buttonStyle = style,
                        onClick = {
                            println("Botão clicado")
                        }
                    )
                    OceanSpacing.StackXS()
                }
            }
            OceanSpacing.StackXS()
        }
    }
}

@Composable
fun OceanButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonStyle: OceanButtonStyle,
    showProgress: Boolean = false,
    icon: Drawable? = null,
    disabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = buttonStyle.getColors(),
        modifier = modifier
            .height(buttonStyle.getHeight()),
        shape = RoundedCornerShape(40.dp),
        enabled = !disabled,
        contentPadding = PaddingValues(
            horizontal = buttonStyle.getHorizontalPadding()
        )
    ) {
        if (!showProgress) {
            if (icon != null) {
                Icon(
                    bitmap = icon.toBitmap().asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )

                Spacer(modifier = Modifier.size(buttonStyle.getIconPadding()))
            }

            Text(
                text = text,
                fontSize = buttonStyle.getTextSize(),
                fontFamily = FontFamilyBaseBold
            )
        } else {
            CircularProgressIndicator(
                color = buttonStyle.getLoadingColor(),
                modifier = Modifier.size(buttonStyle.getHorizontalPadding()),
                strokeWidth = 2.dp
            )
        }
    }
}

private interface ButtonSizes {
    fun getHeight(): Dp
    fun getHorizontalPadding(): Dp
    fun getIconPadding(): Dp = 8.dp
    @Composable
    fun getTextSize() = OceanFontSize.xxs()
}

private interface IButtonColors {
    @Composable fun getColors(): ButtonColors
    @Composable fun getLoadingColor(): Color = OceanColors.interfaceLightUp()
}

private val small = object: ButtonSizes {
    override fun getHeight() = 32.dp
    override fun getHorizontalPadding() = 16.dp
    override fun getIconPadding() = 4.dp
    @Composable
    override fun getTextSize() = OceanFontSize.xxxs()
}
private val medium = object: ButtonSizes {
    override fun getHeight() = 48.dp
    override fun getHorizontalPadding() = 24.dp
}
private val large = object: ButtonSizes {
    override fun getHeight() = 56.dp
    override fun getHorizontalPadding() = 32.dp
}

private val primaryDefaultColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryDefaultButtonColors()
}
private val primaryInverseColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryInverseButtonColors()
}
private val primaryCriticalColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryCriticalButtonColors()
}
private val secondaryDefaultColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryDefaultButtonColors()
    @Composable
    override fun getLoadingColor() = OceanColors.brandPrimaryPure()

}
private val secondaryCriticalColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryCriticalButtonColors()
    @Composable
    override fun getLoadingColor() = OceanColors.brandPrimaryPure()
}

@Immutable
sealed class OceanButtonStyle: ButtonSizes, IButtonColors {
    object PrimarySmall: OceanButtonStyle(),
        IButtonColors by primaryDefaultColors,
        ButtonSizes by small
    object PrimaryMedium: OceanButtonStyle(),
        IButtonColors by primaryDefaultColors,
        ButtonSizes by medium
    object PrimaryLarge: OceanButtonStyle(),
        IButtonColors by primaryDefaultColors,
        ButtonSizes by large

    object PrimaryInverseSmall: OceanButtonStyle(),
        IButtonColors by primaryInverseColors,
        ButtonSizes by small
    object PrimaryInverseMedium: OceanButtonStyle(),
        IButtonColors by primaryInverseColors,
        ButtonSizes by medium
    object PrimaryInverseLarge: OceanButtonStyle(),
        IButtonColors by primaryInverseColors,
        ButtonSizes by large

    object PrimaryCriticalSmall: OceanButtonStyle(),
        IButtonColors by primaryCriticalColors,
        ButtonSizes by small

    object PrimaryCriticalMedium: OceanButtonStyle(),
        IButtonColors by primaryCriticalColors,
        ButtonSizes by medium

    object PrimaryCriticalLarge: OceanButtonStyle(),
        IButtonColors by primaryCriticalColors,
        ButtonSizes by large

    object SecondarySmall: OceanButtonStyle(),
        IButtonColors by secondaryDefaultColors,
        ButtonSizes by small
    object SecondaryMedium: OceanButtonStyle(),
        IButtonColors by secondaryDefaultColors,
        ButtonSizes by medium
    object SecondaryLarge: OceanButtonStyle(),
        IButtonColors by secondaryDefaultColors,
        ButtonSizes by large

    object SecondaryCriticalSmall: OceanButtonStyle(),
        IButtonColors by secondaryCriticalColors,
        ButtonSizes by small
    object SecondaryCriticalMedium: OceanButtonStyle(),
        IButtonColors by secondaryCriticalColors,
        ButtonSizes by medium
    object SecondaryCriticalLarge: OceanButtonStyle(),
        IButtonColors by secondaryCriticalColors,
        ButtonSizes by large
}