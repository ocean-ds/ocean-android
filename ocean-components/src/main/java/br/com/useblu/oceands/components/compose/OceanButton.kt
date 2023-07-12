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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanFontFamily
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
                fontFamily = OceanFontFamily.BaseBold
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
