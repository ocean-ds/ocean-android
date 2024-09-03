package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.border
import br.com.useblu.oceands.model.OceanSettingsStatus
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanSettingsListItemPreview() {
    MaterialTheme {
        Column {

            OceanSettingsListItem(
                title = "Taxa Promocional",
                description = "11,06%",
                newValue = "7,11%",
                actionText = "Entenda o cálculo",
                status = OceanSettingsStatus.CHANGED_TERTIARY,
                showDivider = true,
                onClick = {
                    println("click BLOCKED_ACTIVATED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                actionText = "Click me",
                status = OceanSettingsStatus.DEFAULT,
                showDivider = true,
                onClick = {
                    println("click DEFAULT")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description grande Description grande ",
                caption = "Caption",
                actionText = "Click me",
                error = "Mensagem de erro",
                status = OceanSettingsStatus.ACTIVATED,
                showDivider = true,
                onClick = {
                    println("click ACTIVATED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                actionText = "Click me",
                status = OceanSettingsStatus.BLOCKED,
                showDivider = true,
                onClick = {
                    println("click BLOCKED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                actionText = "Click me",
                status = OceanSettingsStatus.BLOCKED_ACTIVATED,
                showDivider = true,
                error = "Mensagem de erro",
                onClick = {
                    println("click BLOCKED_ACTIVATED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                actionText = "Label",
                caption = "Caption",
                status = OceanSettingsStatus.PENDING,
                onClick = {
                    println("click PENDING")
                }
            )
        }
    }
}

@Composable
fun OceanSettingsListItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    caption: String? = null,
    newValue: String? = null,
    onClick: (() -> Unit)? = null,
    actionText: String? = null,
    status: OceanSettingsStatus,
    error: String? = null,
    showDivider: Boolean = false
) {

    val isStrike = !newValue.isNullOrBlank()

    val decoration = if (isStrike) {
        TextDecoration.LineThrough
    } else null

    Column(modifier) {
        Row(
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xs)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = OceanTextStyle.description
                )

                if (description != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = description,
                            style = OceanTextStyle.paragraph,
                            textDecoration =  if (!newValue.isNullOrBlank()) { decoration } else null,
                            color = if (status == OceanSettingsStatus.ACTIVATED || status == OceanSettingsStatus.BLOCKED) OceanColors.interfaceDarkDeep else OceanColors.interfaceDarkUp
                        )

                        if (!newValue.isNullOrBlank()) {
                            Text(
                                modifier = Modifier.padding(start = OceanSpacing.xxxs),
                                text = newValue,
                                style = OceanTextStyle.paragraph,
                                color = OceanColors.statusPositiveDeep
                            )
                        }
                    }
                }

                if (caption != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = caption,
                        style = OceanTextStyle.caption,
                        color = OceanColors.interfaceDarkDown
                    )
                }

                if (error != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = error,
                        style = OceanTextStyle.caption,
                        color = OceanColors.statusNegativePure
                    )
                }
            }

            val buttonStyle = when (status) {
                OceanSettingsStatus.DEFAULT -> OceanButtonStyle.PrimarySmall
                OceanSettingsStatus.ACTIVATED -> OceanButtonStyle.SecondarySmall
                OceanSettingsStatus.CHANGED_TERTIARY -> OceanButtonStyle.TertiaryMedium
                else -> null
            }

            if (buttonStyle != null && actionText != null) {
                OceanButton(
                    text = actionText,
                    buttonStyle = buttonStyle,
                    onClick = { onClick?.invoke() }
                )
            }

            if (status == OceanSettingsStatus.BLOCKED || status == OceanSettingsStatus.BLOCKED_ACTIVATED) {
                OceanIcon(
                    iconType = OceanIcons.LOCK_CLOSED_SOLID,
                    modifier = Modifier.size(20.dp),
                    tint = OceanColors.interfaceDarkUp
                )
            }

            if (status == OceanSettingsStatus.PENDING) {
                OceanTag(
                    label = actionText ?: "",
                    type = OceanTagType.Warning,
                    showIcon = false
                )
            }
        }

        if (showDivider) {
            HorizontalDivider(
                color = OceanColors.interfaceLightDown
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 680
)
@Composable
private fun SettingsListItemPreview(){
    Column{
        OceanSettingsListItem(
            style = SettingsListItemStyle.ButtonDefault(
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
            style = SettingsListItemStyle.ButtonInverted(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description very large to be able to see the line break",
                ),
                buttonText = "Click me",
                buttonStyle = OceanButtonStyle.TertiarySmall,
                onClick = { println("Button Clicked") }
            ),
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
            style = SettingsListItemStyle.TagInverted(
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
            style = SettingsListItemStyle.BlockedInverted(
                contentStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                icon = OceanIcons.LOCK_OPEN_SOLID
            ),
        )
    }
}


@Composable
fun OceanSettingsListItem(
    modifier: Modifier = Modifier,
    style: SettingsListItemStyle,
    enabled: Boolean = true,
) {
    when(style) {
        is SettingsListItemStyle.ButtonDefault -> {
            SettingsListItemButton(
                modifier = modifier,
                contentStyle = style.contentStyle,
                style = style,
                enabled = enabled,
            )
        }
        is SettingsListItemStyle.ButtonInverted -> {
            SettingsListItemButtonInverted(
                modifier = modifier,
                contentStyle = style.contentStyle,
                style = style,
                enabled = enabled,
            )
        }
        is SettingsListItemStyle.Tag -> {
            SettingsListItemTag(
                modifier = modifier,
                contentStyle = style.contentStyle,
                style = style,
                enabled = enabled,
            )
        }
        is SettingsListItemStyle.TagInverted -> {
            SettingsListItemTagInverted(
                modifier = modifier,
                contentStyle = style.contentStyle,
                style = style,
                enabled = enabled,
            )
        }
        is SettingsListItemStyle.Blocked -> {
            SettingsListItemBlocked(
                modifier = modifier,
                style = style,
                enabled = enabled,
            )
        }
        is SettingsListItemStyle.BlockedInverted -> {
            SettingsListItemBlockedInverted(
                modifier = modifier,
                style = style,
                enabled = enabled,
            )
        }
    }
}

@Composable
private fun SettingsListItemButton(
    modifier : Modifier = Modifier,
    contentStyle: ContentListStyle.Default,
    style: SettingsListItemStyle.ButtonDefault,
    enabled: Boolean,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier
            .border(
                color = OceanColors.interfaceLightDown,
                bottom = 1.dp
            )
            .padding(OceanSpacing.xs)
    ){
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        ) {
            OceanContentList(
                style = contentStyle,
                enabled = enabled,
            )

            if(style.textError.isNotEmpty()){
                OceanText(
                    text = style.textError,
                    style = OceanTextStyle.caption,
                    color = OceanColors.statusNegativePure,
                    maxLines = 2
                )
            }
        }

        OceanButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = style.buttonText,
            buttonStyle = style.buttonStyle,
            disabled = !enabled,
            onClick = style.onClick,
        )
    }
}

@Composable
private fun SettingsListItemButtonInverted(
    modifier : Modifier = Modifier,
    contentStyle: ContentListStyle.Inverted,
    style: SettingsListItemStyle.ButtonInverted,
    enabled: Boolean,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier
            .border(
                color = OceanColors.interfaceLightDown,
                bottom = 1.dp
            )
            .padding(OceanSpacing.xs)
    ){
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        ) {
            OceanContentList(
                style = contentStyle,
                enabled = enabled,
            )

            if(style.textError.isNotEmpty()){
                OceanText(
                    text = style.textError,
                    style = OceanTextStyle.caption,
                    color = OceanColors.statusNegativePure,
                    maxLines = 2
                )
            }
        }

        OceanButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = style.buttonText,
            buttonStyle = style.buttonStyle,
            disabled = !enabled,
            onClick = style.onClick,
        )
    }
}

@Composable
private fun SettingsListItemTag(
    modifier : Modifier = Modifier,
    contentStyle: ContentListStyle.Default,
    style: SettingsListItemStyle.Tag,
    enabled: Boolean,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier
            .border(
                color = OceanColors.interfaceLightDown,
                bottom = 1.dp
            )
            .padding(OceanSpacing.xs)
    ){
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        ) {
            OceanContentList(
                style = contentStyle,
                enabled = enabled,
            )

            if(style.textError.isNotEmpty()){
                OceanText(
                    text = style.textError,
                    style = OceanTextStyle.caption,
                    color = OceanColors.statusNegativePure,
                    maxLines = 2
                )
            }
        }

        OceanTag(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = style.tagStyle,
            enabled = enabled,
        )
    }
}

@Composable
private fun SettingsListItemTagInverted(
    modifier : Modifier = Modifier,
    contentStyle: ContentListStyle.Inverted,
    style: SettingsListItemStyle.TagInverted,
    enabled: Boolean,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier
            .border(
                color = OceanColors.interfaceLightDown,
                bottom = 1.dp
            )
            .padding(OceanSpacing.xs)
    ){
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        ) {
            OceanContentList(
                style = contentStyle,
                enabled = enabled,
            )

            if(style.textError.isNotEmpty()){
                OceanText(
                    text = style.textError,
                    style = OceanTextStyle.caption,
                    color = OceanColors.statusNegativePure,
                    maxLines = 2
                )
            }
        }

        OceanTag(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = style.tagStyle,
            enabled = enabled,
        )
    }
}

@Composable
private fun SettingsListItemBlocked(
    modifier : Modifier = Modifier,
    style: SettingsListItemStyle.Blocked,
    enabled: Boolean,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier
            .border(
                color = OceanColors.interfaceLightDown,
                bottom = 1.dp
            )
            .padding(OceanSpacing.xs)
    ){
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        ) {
            OceanContentList(
                style = style.contentStyle,
                enabled = enabled,
            )
        }

        OceanIcon(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically),
            iconType = style.icon,
            tint = OceanColors.interfaceDarkUp
        )
    }
}

@Composable
private fun SettingsListItemBlockedInverted(
    modifier : Modifier = Modifier,
    style: SettingsListItemStyle.BlockedInverted,
    enabled: Boolean,
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
        modifier = modifier
            .border(
                color = OceanColors.interfaceLightDown,
                bottom = 1.dp
            )
            .padding(OceanSpacing.xs)
    ){
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        ) {
            OceanContentList(
                style = style.contentStyle,
                enabled = enabled,
            )
        }

        OceanIcon(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically),
            iconType = style.icon,
            tint = OceanColors.interfaceDarkUp
        )
    }
}

sealed interface SettingsListItemStyle {
    data class ButtonDefault(
        val contentStyle: ContentListStyle.Default,
        val textError: String = "",
        val buttonText: String,
        val buttonStyle: OceanButtonStyle,
        val onClick: () -> Unit = {},
    ) : SettingsListItemStyle

    data class ButtonInverted(
        val contentStyle: ContentListStyle.Inverted,
        val textError: String = "",
        val buttonText: String,
        val buttonStyle: OceanButtonStyle,
        val onClick: () -> Unit = {},
    ) : SettingsListItemStyle

    data class Tag(
        val contentStyle: ContentListStyle.Default,
        val textError: String = "",
        val tagStyle: OceanTagStyle,
    ) : SettingsListItemStyle

    data class TagInverted(
        val contentStyle: ContentListStyle.Inverted,
        val textError: String = "",
        val tagStyle: OceanTagStyle,
    ) : SettingsListItemStyle

    data class Blocked(
        val contentStyle: ContentListStyle.Default,
        val icon: OceanIcons = OceanIcons.LOCK_CLOSED_SOLID,
    ) : SettingsListItemStyle

    data class BlockedInverted(
        val contentStyle: ContentListStyle.Inverted,
        val icon: OceanIcons = OceanIcons.LOCK_CLOSED_SOLID,
    ) : SettingsListItemStyle
}
