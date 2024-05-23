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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.utils.OceanIcons
import java.util.Locale

@Preview
@Composable
fun OceanLinkPreview() {
    val isSmallOptions = listOf(false, true)

    MaterialTheme {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            isSmallOptions.forEach { isSmall ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (isSmall) "Small" else "Medium",
                        fontSize = 32.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OceanLinkIcon.values().forEach {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = it.name.lowercase()
                                        .replaceFirstChar {
                                            if (it.isLowerCase()) it.titlecase(
                                                Locale.ROOT
                                            ) else it.toString()
                                        },
                                    fontSize = 20.sp
                                )

                                OceanLink(
                                    text = "Link Text",
                                    linkIcon = it,
                                    isSmall = isSmall,
                                    onClick = {}
                                )

                                OceanLink(
                                    text = "Link Text",
                                    type = OceanLinkType.INVERSE,
                                    linkIcon = it,
                                    isSmall = isSmall,
                                    onClick = {}
                                )

                                OceanLink(
                                    text = "Link Text",
                                    type = OceanLinkType.NEUTRAL,
                                    linkIcon = it,
                                    isSmall = isSmall,
                                    onClick = {}
                                )

                                OceanLink(
                                    text = "Link Text",
                                    isDisabled = true,
                                    linkIcon = it,
                                    isSmall = isSmall,
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

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
            val iconModifier = Modifier
                .size(16.dp)
                .padding(start = 2.dp)

            OceanIcon(
                iconType = it,
                modifier = iconModifier,
                tint = textColor
            )
        }
    }
}


enum class OceanLinkType {
    PRIMARY, INVERSE, NEUTRAL;

    @Composable
    fun getTextColor(): Color {
        return when (this) {
            PRIMARY -> OceanColors.brandPrimaryPure
            INVERSE -> OceanColors.complementaryDown
            NEUTRAL -> OceanColors.interfaceDarkDown
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