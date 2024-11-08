package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Deprecated("Use OceanLink with style instead")
@Composable
fun OceanLink(
    text: String,
    modifier: Modifier = Modifier,
    type: OceanLinkType = OceanLinkType.PRIMARY,
    linkIcon: OceanLinkIcon = OceanLinkIcon.DEFAULT,
    isSmall: Boolean = false,
    isDisabled: Boolean = false,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val textColor = if (isDisabled) {
            OceanColors.interfaceDarkUp
        } else {
            type.getTextColor()
        }

        val textSize = if (isSmall) {
            OceanFontSize.xxxs
        } else {
            OceanFontSize.xs
        }

        Text(
            text = text,
            color = textColor,
            fontSize = textSize,
            textDecoration = TextDecoration.Underline,
            fontFamily = OceanFontFamily.BaseMedium
        )

        linkIcon.getIcon()?.let {
            OceanIcon(
                iconType = it,
                tint = textColor,
                modifier = Modifier
                    .padding(start = OceanSpacing.xxxs)
                    .size(16.dp),
            )
        }
    }
}

enum class OceanLinkType {
    PRIMARY, INVERSE, NEUTRAL, WARNING;

    @Composable
    fun getTextColor(): Color {
        return when (this) {
            PRIMARY -> OceanColors.brandPrimaryPure
            INVERSE -> OceanColors.complementaryDown
            NEUTRAL -> OceanColors.interfaceDarkDown
            WARNING -> OceanColors.statusWarningDeep
        }
    }
}

enum class OceanLinkIcon {
    DEFAULT, CHEVRON, EXTERNAL;

    fun getIcon(): OceanIcons? {
        return when (this) {
            DEFAULT -> null
            CHEVRON -> OceanIcons.CHEVRON_RIGHT_SOLID
            EXTERNAL -> OceanIcons.EXTERNAL_LINK_SOLID
        }
    }
}

@Preview
@Composable
fun OceanLinkPreview() {
    val text = "Link Text"
    val stylesOptions = listOf(
        LinkStyle.Medium(text),
        LinkStyle.Small(text),
        LinkStyle.Tiny(text),
    )
    val icons = listOf(
        OceanIcons.UNDEFINED,
        OceanIcons.CHEVRON_RIGHT_SOLID,
        OceanIcons.EXTERNAL_LINK_SOLID
    )
    val colors = listOf(
        Color.Unspecified,
        OceanColors.complementaryDown,
        OceanColors.interfaceDarkDown
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            stylesOptions.forEach { style ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OceanText(
                        text = when (style) {
                            is LinkStyle.Tiny -> "Tiny"
                            is LinkStyle.Small -> "Small"
                            else -> "Medium"
                        },
                        fontSize = OceanFontSize.lg
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        icons.forEach { icon ->
                            Column {
                                OceanText(
                                    text = when (icon) {
                                        OceanIcons.CHEVRON_RIGHT_SOLID -> "Chevron"
                                        OceanIcons.EXTERNAL_LINK_SOLID -> "External"
                                        else -> "Default"
                                    },
                                    fontSize = OceanFontSize.sm
                                )
                                colors.forEachIndexed { index, color ->
                                    ShowLink(style, color, icon, true)
                                }
                                ShowLink(style, Color.Unspecified, icon, false)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowLink(
    style: LinkStyle,
    color: Color,
    icon: OceanIcons,
    enabled: Boolean
) {
    when (style) {
        is LinkStyle.Medium -> OceanLink(
            style = style.copy(
                color = color,
                icon = icon
            ),
            enabled = enabled,
            onClick = {},
        )

        is LinkStyle.Small -> OceanLink(
            style = style.copy(
                color = color,
                icon = icon
            ),
            enabled = enabled,
            onClick = {},
        )

        is LinkStyle.Tiny -> {
            OceanLink(
                style = style.copy(
                    color = color,
                    icon = icon
                ),
                enabled = enabled,
                onClick = {},
            )
        }
    }
}

@Composable
fun OceanLink(
    style: LinkStyle,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    when (style) {
        is LinkStyle.Medium -> MediumLink(
            style = style,
            onClick = onClick,
            enabled = enabled
        )

        is LinkStyle.Small -> SmallLink(
            style = style,
            onClick = onClick,
            enabled = enabled
        )

        is LinkStyle.Tiny -> TinyLink(
            style = style,
            onClick = onClick,
            enabled = enabled
        )
    }
}

@Composable
private fun MediumLink(
    style: LinkStyle.Medium,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    LinkText(
        text = style.text,
        style = TextStyle(
            color = if (enabled) style.color else OceanColors.interfaceDarkUp,
            fontSize = OceanFontSize.xs,
        ),
        enabled = enabled,
        icon = style.icon,
        iconSize = 16.dp,
        onClick = onClick,
    )
}

@Composable
private fun SmallLink(
    style: LinkStyle.Small,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    LinkText(
        text = style.text,
        style = TextStyle(
            color = if (enabled) style.color else OceanColors.interfaceDarkUp,
            fontSize = OceanFontSize.xxs,
        ),
        enabled = enabled,
        icon = style.icon,
        iconSize = 16.dp,
        onClick = onClick,
    )
}

@Composable
private fun TinyLink(
    style: LinkStyle.Tiny,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    LinkText(
        text = style.text,
        style = TextStyle(
            color = if (enabled) style.color else OceanColors.interfaceDarkUp,
            fontSize = OceanFontSize.xxxs,
        ),
        enabled = enabled,
        icon = style.icon,
        iconSize = 14.dp,
        onClick = onClick,
    )
}

@Composable
private fun LinkText(
    text: String,
    style: TextStyle,
    icon: OceanIcons,
    iconSize: Dp,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val color =
        if (style.color != Color.Unspecified) style.color
        else OceanColors.brandPrimaryPure

    Row(
        modifier = Modifier
            .clickable(enabled = enabled, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OceanText(
            text = text,
            color = color,
            fontSize = style.fontSize,
            textDecoration = TextDecoration.Underline,
            fontFamily = OceanFontFamily.BaseMedium
        )

        if (icon != OceanIcons.UNDEFINED) {
            OceanIcon(
                iconType = icon,
                tint = color,
                modifier = Modifier
                    .padding(start = OceanSpacing.xxxs)
                    .size(iconSize),
            )
        }
    }
}

sealed interface LinkStyle {
    val text: String
    val color: Color
    val icon: OceanIcons

    data class Medium(
        override val text: String,
        override val color: Color = Color.Unspecified,
        override val icon: OceanIcons = OceanIcons.UNDEFINED,
    ) : LinkStyle

    data class Small(
        override val text: String,
        override val color: Color = Color.Unspecified,
        override val icon: OceanIcons = OceanIcons.UNDEFINED,
    ) : LinkStyle

    data class Tiny(
        override val text: String,
        override val color: Color = Color.Unspecified,
        override val icon: OceanIcons = OceanIcons.UNDEFINED,
    ) : LinkStyle
}