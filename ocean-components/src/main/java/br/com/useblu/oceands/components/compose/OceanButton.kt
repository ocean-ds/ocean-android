package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Preview(widthDp = 480)
@Composable
fun PreviewButton() {
    val icon = OceanIcons.CHEVRON_DOWN_OUTLINE

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
        ),
        listOf(
            OceanButtonStyle.TertiarySmall,
            OceanButtonStyle.TertiaryMedium,
            OceanButtonStyle.TertiaryLarge,
        ),
        listOf(
            OceanButtonStyle.TertiaryCriticalSmall,
            OceanButtonStyle.TertiaryCriticalMedium,
            OceanButtonStyle.TertiaryCriticalLarge,
        )
    )

    OceanTheme {
        Column(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure)
        ) {
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

            OceanButton(
                text = "",
                showProgress = false,
                icon = icon,
                disabled = false,
                modifier = Modifier,
                buttonStyle = OceanButtonStyle.PrimaryMedium,
                onClick = {
                    println("Botão clicado")
                }
            )

            var isLoading by remember {
                mutableStateOf(false)
            }
            OceanButton(
                text = "Permitir",
                showProgress = isLoading,
                modifier = Modifier
                    .padding(top = OceanSpacing.xs)
                    .width(200.dp),
                buttonStyle = OceanButtonStyle.PrimaryMedium,
                onClick = {
                    println("Botão clicado")
                    isLoading = true
                }
            )
        }
    }
}

enum class Orientation {
    Horizontal, Vertical
}

data class OceanButtonModel(
    val text: String,
    val onClick: () -> Unit,
    val buttonStyle: OceanButtonStyle,
    val showProgress: Boolean = false,
    val icon: OceanIcons? = null,
    val disabled: Boolean = false
)

@Composable
fun OceanButton(
    modifier: Modifier = Modifier,
    button: OceanButtonModel
) {
    OceanButton(
        text = button.text,
        onClick = button.onClick,
        buttonStyle = button.buttonStyle,
        modifier = modifier,
        showProgress = button.showProgress,
        icon = button.icon,
        disabled = button.disabled
    )
}

@Composable
fun OceanButton(
    text: String,
    onClick: () -> Unit,
    buttonStyle: OceanButtonStyle,
    modifier: Modifier = Modifier,
    showProgress: Boolean = false,
    icon: OceanIcons? = null,
    disabled: Boolean = false
) {
    Button(
        onClick = { if (!showProgress) onClick.invoke() },
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
                    painter = painterResource(id = icon.icon),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                )
            }

            if (icon != null && text.isNotEmpty()) {
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
