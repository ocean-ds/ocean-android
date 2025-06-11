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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.model.OceanInlineTextList
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanTransactionFooterPreview() {
    val entries = listOf(
        OceanInlineTextList(
            label = "Label1",
            value = "R$ 40,00",
            newValue = "Zero",
            color = "colorStatusPositiveDeep",
            icon = "tagsolid"
        ),
        OceanInlineTextList(
            label = "Label1 - teste",
            value = "R$ 40,00",
            newValue = "",
            color = "colorStatusPositiveDeep"
        ),
        OceanInlineTextList(
            label = "Label2",
            value = "R$ 40,00",
            color = "colorInterfaceDarkDown"

        ),
        OceanInlineTextList(
            label = "Label3",
            value = "Calculada no dia",
            color = "colorStatusNeutralDeep"

        ),
        OceanInlineTextList(
            label = "Label4",
            value = "R$ 10,00",
            tooltip = "tooltip",
            color = "colorStatusPositiveDeep",
            icon = "tagsolid"

        ),
        OceanInlineTextList(
            label = "Label5",
            value = "R$ 40,00",
            color = "colorInterfaceDarkPure",
            isBold = true,
            icon = OceanIcons.BRIEFCASE_OUTLINE.token

        )
    )

    OceanTheme {
        OceanTransactionFooter(
            entries = entries,
            firstButton = OceanButtonModel(
                text = "Avançar",
                onClick = {},
                buttonStyle = OceanButtonStyle.PrimaryMedium
            ),
            secondButton = OceanButtonModel(
                text = "Voltar",
                onClick = {},
                buttonStyle = OceanButtonStyle.SecondaryMedium
            ),
            buttonsOrientation = Orientation.Vertical
        )
    }
}

@Composable
fun OceanTransactionFooter(
    modifier: Modifier = Modifier,
    entries: List<OceanInlineTextList>,
    firstButton: OceanButtonModel,
    secondButton: OceanButtonModel? = null,
    entriesSpacing: Dp = OceanSpacing.xxs,
    buttonsOrientation: Orientation = Orientation.Vertical
) {
    Column(
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .padding(OceanSpacing.xs),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(entriesSpacing)
        ) {
            entries.forEach { item ->
                OceanInlineTextListItem(
                    item = item
                )
            }
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
