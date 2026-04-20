package br.com.useblu.oceands.components.compose.textlistreadonly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanContentList
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

enum class OceanTextListReadOnlyState {
    Default,
    Disabled,
    Loading
}

@Composable
fun OceanTextListReadOnly(
    modifier: Modifier = Modifier,
    contentListStyle: ContentListStyle,
    state: OceanTextListReadOnlyState = OceanTextListReadOnlyState.Default,
    showIcon: Boolean = false,
    icon: OceanIcons? = null,
    iconTint: Color = OceanColors.interfaceDarkUp,
    iconSize: Dp? = null,
    showIndicator: Boolean = false,
    indicator: OceanTagStyle? = null,
    indicatorLayout: OceanTagLayout = OceanTagLayout.Medium(),
    showDivider: Boolean = false
) {
    val enabled = state != OceanTextListReadOnlyState.Disabled
    val isLoading = state == OceanTextListReadOnlyState.Loading

    Column(
        modifier = modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row(
            modifier = Modifier.padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            if (showIcon && icon != null) {
                val iconModifier = iconSize?.let { Modifier.size(it) } ?: Modifier
                OceanIcon(
                    iconType = icon,
                    modifier = iconModifier,
                    tint = if (enabled) iconTint else OceanColors.interfaceDarkUp
                )
            }

            OceanContentList(
                modifier = Modifier.weight(1f),
                style = contentListStyle,
                isLoading = isLoading,
                enabled = enabled
            )

            if (showIndicator && indicator != null && !isLoading) {
                OceanTag(
                    style = indicator,
                    enabled = enabled
                )
            }
        }

        if (showDivider) {
            OceanDivider(
                modifier = Modifier.padding(horizontal = OceanSpacing.xs)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OceanTextListReadOnlyInvertedPreview() = OceanTheme {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Saldo total na Blu",
                description = "R$ 2.500,00"
            )
        )
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Saldo total na Blu",
                description = "R$ 2.500,00"
            ),
            showDivider = true
        )
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Saldo total na Blu",
                description = "R$ 2.500,00"
            ),
            showIcon = true,
            icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE
        )
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Saldo total na Blu",
                description = "R$ 2.500,00"
            ),
            showIndicator = true,
            indicator = OceanTagStyle.Default(
                label = "Ativo",
                layout = OceanTagLayout.Medium(),
                type = OceanTagType.Positive
            )
        )
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Saldo total na Blu",
                description = "R$ 2.500,00",
                caption = "Atualizado agora"
            ),
            showIcon = true,
            icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
            showIndicator = true,
            indicator = OceanTagStyle.Default(
                label = "Novo",
                layout = OceanTagLayout.Medium(),
                type = OceanTagType.Complementary
            ),
            showDivider = true
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun OceanTextListReadOnlyDefaultPreview() = OceanTheme {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        )
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description"
            ),
            state = OceanTextListReadOnlyState.Disabled
        )
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description"
            ),
            state = OceanTextListReadOnlyState.Loading
        )
    }
}
