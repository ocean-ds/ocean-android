package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanInlineTextList
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground

enum class OceanTransactionFooterVariant {
    Default,
    Highlight
}

@Composable
fun OceanTransactionFooter(
    modifier: Modifier = Modifier,
    entries: List<OceanInlineTextList>,
    firstButton: OceanButtonModel,
    secondButton: OceanButtonModel? = null,
    entriesSpacing: Dp = OceanSpacing.xxs,
    buttonsOrientation: Orientation = Orientation.Vertical,
    caption: String = "",
    variant: OceanTransactionFooterVariant = OceanTransactionFooterVariant.Default,
    sectionTitle: String = "",
    showBottomDivider: Boolean = false
) {
    val backgroundModifier = when (variant) {
        OceanTransactionFooterVariant.Default ->
            Modifier.background(OceanColors.interfaceLightPure)

        OceanTransactionFooterVariant.Highlight ->
            Modifier.borderBackground(
                color = OceanColors.interfaceLightUp,
                borderRadius = OceanBorderRadius.LG.topCorners
            )
    }

    // Divisor de topo da variante Default (Figma) — desenhado no topo, edge-to-edge,
    // sem alterar o padding do conteúdo. A Highlight arredonda o topo e não tem divisor.
    val dividerColor = OceanColors.interfaceLightDown
    val topDividerModifier = if (variant == OceanTransactionFooterVariant.Default) {
        Modifier.drawBehind {
            drawLine(
                color = dividerColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 1.dp.toPx()
            )
        }
    } else {
        Modifier
    }

    Column(
        modifier = modifier
            .then(backgroundModifier)
            .then(topDividerModifier)
            .padding(OceanSpacing.xs),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(entriesSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OceanTextNotBlank(
                text = sectionTitle,
                modifier = Modifier.fillMaxWidth(),
                color = OceanColors.interfaceDarkUp,
                style = OceanTextStyle.heading5
            )
            entries.forEachIndexed { index, item ->
                if (showBottomDivider && index == entries.lastIndex && index > 0) {
                    OceanDivider(
                        modifier = Modifier.padding(vertical = OceanSpacing.xxxs)
                    )
                }
                OceanInlineTextListItem(
                    item = item
                )
            }
            OceanTextNotBlank(
                text = caption,
                style = OceanTextStyle.paragraph,
                color = OceanColors.interfaceDarkUp
            )
        }

        val buttons: @Composable (Modifier) -> Unit = {
            OceanButton(
                showProgress = firstButton.showProgress,
                text = firstButton.text,
                buttonStyle = firstButton.buttonStyle,
                icon = firstButton.icon,
                onClick = firstButton.onClick,
                modifier = it
            )

            if (secondButton != null) {
                OceanButton(
                    showProgress = secondButton.showProgress,
                    text = secondButton.text,
                    icon = secondButton.icon,
                    buttonStyle = secondButton.buttonStyle,
                    onClick = secondButton.onClick,
                    modifier = it
                )
            }
        }

        when (buttonsOrientation) {
            Orientation.Horizontal -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    buttons(Modifier.weight(1f))
                }
            }

            Orientation.Vertical -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
                ) {
                    buttons(Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Preview
@Composable
private fun OceanTransactionFooterPreview() {
    val entries = listOf(
        OceanInlineTextList(
            label = "Title",
            value = "Description",
            color = "colorInterfaceDarkDown"
        ),
        OceanInlineTextList(
            label = "Title",
            value = "Description",
            color = "colorInterfaceDarkDown"
        ),
        OceanInlineTextList(
            label = "Title",
            value = "Description",
            color = "colorInterfaceDarkPure",
            isBold = true
        )
    )

    OceanTheme {
        OceanTransactionFooter(
            entries = entries,
            firstButton = OceanButtonModel(
                text = "Label",
                onClick = {},
                buttonStyle = OceanButtonStyle.PrimaryMedium
            ),
            variant = OceanTransactionFooterVariant.Default,
            sectionTitle = "Title",
            showBottomDivider = true
        )
    }
}

@Preview
@Composable
private fun OceanTransactionFooterHighlightPreview() {
    val entries = listOf(
        OceanInlineTextList(
            label = "Title",
            value = "Description",
            color = "colorInterfaceDarkDown"
        ),
        OceanInlineTextList(
            label = "Title",
            value = "Description",
            color = "colorInterfaceDarkDown"
        ),
        OceanInlineTextList(
            label = "Title",
            value = "Description",
            color = "colorInterfaceDarkPure",
            isBold = true
        )
    )

    OceanTheme {
        OceanTransactionFooter(
            entries = entries,
            firstButton = OceanButtonModel(
                text = "Label",
                onClick = {},
                buttonStyle = OceanButtonStyle.PrimaryMedium
            ),
            variant = OceanTransactionFooterVariant.Highlight,
            sectionTitle = "Title",
            showBottomDivider = true
        )
    }
}
