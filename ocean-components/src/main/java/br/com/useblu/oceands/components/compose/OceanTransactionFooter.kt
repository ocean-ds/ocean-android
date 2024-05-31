package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanTransactionFooterItem
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanTransactionFooterPreview() {
    val entries = listOf(
        OceanTransactionFooterItem(
            label = "Label0",
            value = "R$ 40,00",
            color = "colorStatusPositiveDeep",
            icon = "tagsolid"
        ),
        OceanTransactionFooterItem(
            label = "Label1",
            value = "R$ 40,00",
            newValue = "Zero",
            color = "colorStatusPositiveDeep",
            icon = "tagsolid"
        ),
        OceanTransactionFooterItem(
            label = "Label2",
            value = "R$ 10,00",
            color = "colorStatusPositiveDeep",
        ),
        OceanTransactionFooterItem(
            label = "Label3",
            value = "Zero",
            color = "colorStatusPositiveDeep",
        ),
        OceanTransactionFooterItem(
            label = "Label4",
            value = "R$ 40,00",
            color = "colorInterfaceDarkDown",
        ),
        OceanTransactionFooterItem(
            label = "Label5",
            value = "Calculada no dia",
            color = "colorStatusNeutralDeep",
        ),
        OceanTransactionFooterItem(
            label = "Label6",
            value = "R$ 10,00",
            tooltip = "tooltip",
            color = "colorStatusPositiveDeep",
            icon = "tagsolid"
        ),
        OceanTransactionFooterItem(
            label = "Label7",
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
            buttonsOrientation = Orientation.Horizontal
        )
    }
}

@Composable
fun OceanTransactionFooter(
    modifier: Modifier = Modifier,
    entries: List<OceanTransactionFooterItem>,
    firstButton: OceanButtonModel,
    secondButton: OceanButtonModel? = null,
    buttonsOrientation: Orientation = Orientation.Vertical
) {
    Column(
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .padding(OceanSpacing.xs),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.sm)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            entries.forEach { item ->
                TransactionFooterItem(
                    item = item
                )
            }
        }

        val buttons: @Composable (Modifier) -> Unit = {
            OceanButton(
                text = firstButton.text,
                buttonStyle = firstButton.buttonStyle,
                icon = firstButton.icon,
                onClick = firstButton.onClick,
                modifier = it
            )

            if (secondButton != null) {
                OceanButton(
                    text = secondButton.text,
                    icon = secondButton.icon,
                    buttonStyle = OceanButtonStyle.SecondaryMedium,
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

@Composable
private fun TransactionFooterItem(
    item: OceanTransactionFooterItem
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OceanText(
                text = item.label ?: "",
                style = OceanTextStyle.paragraph
            )

            if (!item.tooltip.isNullOrBlank()) {
                OceanTooltip(item.tooltip) {
                    Icon(
                        painter = painterResource(id = R.drawable.ocean_icon_info_solid),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = OceanSpacing.xxxs)
                            .size(OceanSpacing.xs),
                        tint = OceanColors.interfaceLightDeep
                    )
                }

            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!item.icon.isNullOrBlank()) {
                OceanIcon(
                    iconType = OceanIcons.fromToken(item.icon),
                    modifier = Modifier.size(OceanSpacing.xs),
                    tint = item.color?.let { OceanColors.fromString(color = item.color) }
                        ?: Color.Unspecified
                )
            }

            val textColor = if (item.newValue == null || item.value.isNullOrBlank()) {
                OceanColors.fromString(color = item.color ?: "")
            } else OceanColors.interfaceDarkDown

            val valueStyle = if (item.isBold == true) {
                OceanTextStyle.paragraph.copy(
                    fontFamily = OceanFontFamily.BaseBold
                )
            } else OceanTextStyle.paragraph

            val valueDecoration = if (item.isStrike == true) {
                TextDecoration.LineThrough
            } else null

            OceanText(
                text = item.value ?: "",
                style = valueStyle,
                color = textColor,
                modifier = Modifier.padding(start = OceanSpacing.xxxs),
                textDecoration = valueDecoration
            )

            if (!item.newValue.isNullOrBlank()) {
                val color = item.color?.let {
                    OceanColors.fromString(color = it)
                }
                OceanText(
                    text = item.newValue,
                    color = color ?: Color.Unspecified,
                    style = OceanTextStyle.paragraph,
                    modifier = Modifier.padding(start = OceanSpacing.xxxs)
                )
            }
        }
    }
}