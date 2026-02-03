package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Preview()
@Composable
fun PreviewButtonInteractive() {
    var selectedStyle by remember { mutableStateOf<OceanButtonStyle>(OceanButtonStyle.PrimaryMedium) }
    var showIcon by remember { mutableStateOf(true) }
    var isDisabled by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf("Medium") }
    var selectedVariant by remember { mutableStateOf("Primary") }

    val icon = if (showIcon) OceanIcons.CHEVRON_DOWN_OUTLINE else null

    fun updateStyle() {
        selectedStyle = when (selectedVariant) {
            "Primary" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.PrimarySmall
                "Medium" -> OceanButtonStyle.PrimaryMedium
                "Large" -> OceanButtonStyle.PrimaryLarge
                else -> OceanButtonStyle.PrimaryMedium
            }
            "PrimaryInverse" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.PrimaryInverseSmall
                "Medium" -> OceanButtonStyle.PrimaryInverseMedium
                "Large" -> OceanButtonStyle.PrimaryInverseLarge
                else -> OceanButtonStyle.PrimaryInverseMedium
            }
            "PrimaryCritical" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.PrimaryCriticalSmall
                "Medium" -> OceanButtonStyle.PrimaryCriticalMedium
                "Large" -> OceanButtonStyle.PrimaryCriticalLarge
                else -> OceanButtonStyle.PrimaryCriticalMedium
            }
            "PrimaryWarning" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.PrimaryWarningSmall
                "Medium" -> OceanButtonStyle.PrimaryWarningMedium
                "Large" -> OceanButtonStyle.PrimaryWarningLarge
                else -> OceanButtonStyle.PrimaryWarningMedium
            }
            "Secondary" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.SecondarySmall
                "Medium" -> OceanButtonStyle.SecondaryMedium
                "Large" -> OceanButtonStyle.SecondaryLarge
                else -> OceanButtonStyle.SecondaryMedium
            }
            "SecondaryCritical" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.SecondaryCriticalSmall
                "Medium" -> OceanButtonStyle.SecondaryCriticalMedium
                "Large" -> OceanButtonStyle.SecondaryCriticalLarge
                else -> OceanButtonStyle.SecondaryCriticalMedium
            }
            "SecondaryWarning" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.SecondaryWarningSmall
                "Medium" -> OceanButtonStyle.SecondaryWarningMedium
                "Large" -> OceanButtonStyle.SecondaryWarningLarge
                else -> OceanButtonStyle.SecondaryWarningMedium
            }
            "Tertiary" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.TertiarySmall
                "Medium" -> OceanButtonStyle.TertiaryMedium
                "Large" -> OceanButtonStyle.TertiaryLarge
                else -> OceanButtonStyle.TertiaryMedium
            }
            "TertiaryCritical" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.TertiaryCriticalSmall
                "Medium" -> OceanButtonStyle.TertiaryCriticalMedium
                "Large" -> OceanButtonStyle.TertiaryCriticalLarge
                else -> OceanButtonStyle.TertiaryCriticalMedium
            }
            "TertiaryWarning" -> when (selectedSize) {
                "Small" -> OceanButtonStyle.TertiaryWarningSmall
                "Medium" -> OceanButtonStyle.TertiaryWarningMedium
                "Large" -> OceanButtonStyle.TertiaryWarningLarge
                else -> OceanButtonStyle.TertiaryWarningMedium
            }
            else -> OceanButtonStyle.PrimaryMedium
        }
    }

    OceanTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = OceanColors.interfaceLightPure)
                .padding(OceanSpacing.md)
        ) {
            OceanText(
                text = "Preview",
                fontSize = OceanFontSize.xs,
                fontFamily = OceanFontFamily.BaseBold
            )

            OceanSpacing.StackXS()

            OceanButton(
                text = "Avançar",
                showProgress = isLoading,
                icon = icon,
                disabled = isDisabled,
                modifier = Modifier,
                buttonStyle = selectedStyle,
                onClick = { }
            )

            OceanSpacing.StackMD()

            OceanText(
                text = "Controles",
                fontSize = OceanFontSize.xs,
                fontFamily = OceanFontFamily.BaseBold
            )

            OceanSpacing.StackXS()

            OceanText(text = "Tamanho:", fontSize = OceanFontSize.xxxs)
            Row {
                listOf("Small", "Medium", "Large").forEach { size ->
                    OceanButton(
                        text = size,
                        buttonStyle = if (selectedSize == size)
                            OceanButtonStyle.PrimarySmall
                        else
                            OceanButtonStyle.SecondarySmall,
                        onClick = {
                            selectedSize = size
                            updateStyle()
                        }
                    )
                    OceanSpacing.StackXS()
                }
            }

            OceanSpacing.StackXS()

            OceanText(text = "Variante:", fontSize = OceanFontSize.xxxs)
            Column {
                listOf(
                    "Primary",
                    "PrimaryInverse",
                    "PrimaryCritical",
                    "PrimaryWarning",
                    "Secondary",
                    "SecondaryCritical",
                    "SecondaryWarning",
                    "Tertiary",
                    "TertiaryCritical",
                    "TertiaryWarning"
                ).forEach { variant ->
                    Row {
                        OceanButton(
                            text = variant,
                            buttonStyle = if (selectedVariant == variant)
                                OceanButtonStyle.PrimarySmall
                            else
                                OceanButtonStyle.SecondarySmall,
                            onClick = {
                                selectedVariant = variant
                                updateStyle()
                            }
                        )
                    }
                    OceanSpacing.StackXXS()
                }
            }

            OceanSpacing.StackXS()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = OceanSpacing.xxxs)
            ) {
                OceanButton(
                    text = if (showIcon) "Esconder Ícone" else "Mostrar Ícone",
                    buttonStyle = OceanButtonStyle.TertiarySmall,
                    onClick = { showIcon = !showIcon }
                )
                OceanSpacing.StackXS()
                OceanButton(
                    text = if (isDisabled) "Habilitar" else "Desabilitar",
                    buttonStyle = OceanButtonStyle.TertiarySmall,
                    onClick = { isDisabled = !isDisabled }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OceanButton(
                    text = if (isLoading) "Esconder Loading" else "Mostrar Loading",
                    buttonStyle = OceanButtonStyle.TertiarySmall,
                    onClick = { isLoading = !isLoading }
                )
            }
        }
    }
}
enum class Orientation {
    Horizontal,
    Vertical
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
        shape = OceanBorderRadius.Circle.allCorners.shape(),
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
                    modifier = Modifier.size(22.dp)
                )
            }

            if (icon != null && text.isNotEmpty()) {
                Spacer(modifier = Modifier.size(buttonStyle.getIconPadding()))
            }

            OceanText(
                text = text,
                fontSize = buttonStyle.getTextSize(),
                fontFamily = OceanFontFamily.BaseBold,
                textAlign = TextAlign.Center
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
