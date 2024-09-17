package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 1000
)
@Composable
private fun SettingsListItemPreview() {
    Column {
        OceanSettingsListItem(
            style = SettingsListItemStyle.Button(
                contentStyle = ContentListStyle.Default(
                    title = "Taxa Promocional",
                    description = "<span style=\"color: #AAADC0;\"><strike>11,06%</strike></span> <span style=\"color: #2DA94F;\">7,11%</span>\n",
                ),
                buttonText = "Entenda o cálculo",
                buttonStyle = OceanButtonStyle.TertiarySmall,
                onClick = { println("Button Clicked") }
            ),
        )

        OceanSettingsListItem(
            style = SettingsListItemStyle.Button(
                contentStyle = ContentListStyle.Strikethrough(
                    title = "Taxa Promocional - Strikethrough",
                    description = "11.06%",
                    newValue = "7.33%",
                ),
                textError = "Error",
                buttonText = "Entenda o cálculo",
                buttonStyle = OceanButtonStyle.TertiarySmall,
                onClick = { println("Button Clicked") }
            ),
        )

        OceanSettingsListItem(
            style = SettingsListItemStyle.Button(
                contentStyle = ContentListStyle.Default(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                textError = "Error",
                buttonText = "Click me",
                buttonStyle = OceanButtonStyle.PrimarySmall,
                onClick = { println("Button Clicked") }
            ),
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Button(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description very large to be able to see the line break",
                ),
                textError = "Error",
                buttonText = "Click me",
                buttonStyle = OceanButtonStyle.TertiarySmall,
                onClick = { println("Button Clicked") }
            ),
        )


        OceanSettingsListItem(
            style = SettingsListItemStyle.Button(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                ),
                buttonText = "Click me",
                buttonStyle = OceanButtonStyle.TertiarySmall,
                onClick = { println("Button Clicked") }
            ),
            enabled = false
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Tag(
                contentStyle = ContentListStyle.Default(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                textError = "Error",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive,
                )
            ),
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Tag(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                textError = "Error",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Negative,
                )
            ),
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Tag(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Negative,
                )
            ),
            enabled = false
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Blocked(
                contentStyle = ContentListStyle.Default(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                icon = OceanIcons.LOCK_CLOSED_SOLID
            ),
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Blocked(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                icon = OceanIcons.LOCK_OPEN_SOLID
            ),
        )
        OceanSettingsListItem(
            style = SettingsListItemStyle.Button(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                buttonStyle = OceanButtonStyle.PrimaryCriticalSmall,
                buttonText = "Click me",
                onClick = { println("Button Clicked") }
            ),
            isLoading = true
        )
    }
}


@Composable
fun OceanSettingsListItem(
    modifier: Modifier = Modifier,
    style: SettingsListItemStyle,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    showDivider: Boolean = false
) {
    if (isLoading) {
        SettingsListItemSkeleton()
        return
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier.padding(OceanSpacing.xs)
    ) {
        when (style) {
            is SettingsListItemStyle.Blocked -> {
                SettingsListTextContent(
                    modifier = Modifier.weight(1f),
                    style = style.contentStyle,
                    enabled = enabled
                )

                OceanIcon(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically),
                    iconType = style.icon,
                    tint = OceanColors.interfaceDarkUp
                )
            }

            is SettingsListItemStyle.Button -> {
                SettingsListTextContent(
                    modifier = Modifier.weight(1f),
                    style = style.contentStyle,
                    enabled = enabled
                )

                OceanButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = style.buttonText,
                    buttonStyle = style.buttonStyle,
                    disabled = !enabled,
                    onClick = style.onClick,
                )
            }

            is SettingsListItemStyle.Tag -> {
                SettingsListTextContent(
                    modifier = Modifier.weight(1f),
                    style = style.contentStyle,
                    enabled = enabled
                )

                OceanTag(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = style.tagStyle,
                    enabled = enabled,
                )
            }
        }
    }

    if (showDivider) {
        HorizontalDivider(
            color = OceanColors.interfaceLightDown
        )
    }
}

@Composable
private fun SettingsListItemSkeleton() {
    OceanShimmering { brush ->
        Row(
            modifier = Modifier.padding(OceanSpacing.xs)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
                modifier = Modifier.weight(1f),
            ) {
                repeat(2) {
                    Spacer(
                        modifier = Modifier
                            .background(brush, RoundedCornerShape(4.dp))
                            .width(((it + 1) * 100).dp)
                            .height(16.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .background(brush, RoundedCornerShape(16.dp))
                    .width(100.dp)
                    .height(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun SettingsListTextContent(
    modifier: Modifier = Modifier,
    style: ContentListStyle,
    enabled: Boolean,
    textError: String = ""
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
    ) {
        OceanContentList(
            style = style,
            enabled = enabled,
        )

        if (textError.isNotEmpty()) {
            OceanText(
                text = textError,
                style = OceanTextStyle.caption,
                color = OceanColors.statusNegativePure,
                maxLines = 2
            )
        }
    }
}

sealed interface SettingsListItemStyle {
    data class Button(
        val contentStyle: ContentListStyle,
        val textError: String = "",
        val buttonText: String,
        val buttonStyle: OceanButtonStyle,
        val onClick: () -> Unit,
    ) : SettingsListItemStyle

    data class Tag(
        val contentStyle: ContentListStyle,
        val textError: String = "",
        val tagStyle: OceanTagStyle,
    ) : SettingsListItemStyle

    data class Blocked(
        val contentStyle: ContentListStyle,
        val icon: OceanIcons = OceanIcons.LOCK_CLOSED_SOLID,
    ) : SettingsListItemStyle
}
