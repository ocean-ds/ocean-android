package br.com.useblu.oceands.components.compose.cardlistitem

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemContentStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemContentStyle.Default.getDescriptionStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemContentStyle.Default.getTitleStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.components.compose.cardlistitem.style.DefaultCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.style.HighlightedCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.type.LeadingDefaultTypeCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.type.TrailingSelectableCardListItem
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

enum class OceanCardListItemTagAlignment {
    START,
    END
}

@Composable
fun OceanCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    tagStyle: OceanTagStyle? = null,
    tagAlignment: OceanCardListItemTagAlignment = OceanCardListItemTagAlignment.START,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    style: OceanCardListItemStyle = OceanCardListItemStyle.Default,
    disabled: Boolean = false,
    isSelected: Boolean = false,
    showChevron: Boolean = false,
    onClick: (() -> Unit)? = null,
    onDisabledClick: (() -> Unit)? = null
) {
    when (style) {
        is OceanCardListItemStyle.Default -> {
            DefaultCardListItem(
                modifier = modifier,
                title = title,
                description = description,
                caption = caption,
                contentStyle = contentStyle,
                tagStyle = tagStyle,
                tagAlignment = tagAlignment,
                type = type,
                disabled = disabled,
                isSelected = isSelected,
                showChevron = showChevron,
                onClick = onClick,
                onDisabledClick = onDisabledClick
            )
        }

        is OceanCardListItemStyle.Highlighted -> {
            HighlightedCardListItem(
                modifier = modifier,
                title = title,
                description = description,
                caption = caption,
                tagStyle = tagStyle,
                tagAlignment = tagAlignment,
                type = type,
                style = style,
                disabled = disabled,
                isSelected = isSelected,
                showChevron = showChevron,
                onClick = onClick,
                onDisabledClick = onDisabledClick
            )
        }
    }
}

@Composable
internal fun DeprecatedTransitionCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    style: OceanCardListItemStyle = OceanCardListItemStyle.Default,
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    OceanCardListItem(
        modifier = modifier,
        title = title,
        description = description,
        caption = caption,
        tagStyle = tagStyle,
        type = type,
        style = style,
        disabled = disabled,
        isSelected = isSelected,
        onClick = onClick
    )
}

@Composable
internal fun ContentCardListItem(
    title: String,
    description: String = "",
    caption: String,
    tagStyle: OceanTagStyle?,
    tagAlignment: OceanCardListItemTagAlignment = OceanCardListItemTagAlignment.START,
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    type: OceanCardListItemType,
    style: OceanCardListItemStyle,
    isSelected: Boolean,
    disabled: Boolean,
    showChevron: Boolean = false
) {
    val hasSelectableType = type is OceanCardListItemType.Selectable
    val isTagAlignedEndWithSelectable = tagAlignment == OceanCardListItemTagAlignment.END && hasSelectableType

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(OceanSpacing.xs)
    ) {
        LeadingContentCardListItem(
            type = type,
            style = style,
            isSelected = isSelected,
            disabled = disabled
        )

        CenterContent(
            modifier = Modifier.weight(1f),
            title = title,
            description = description,
            caption = caption,
            contentStyle = contentStyle,
            tagStyle = if (isTagAlignedEndWithSelectable) null else tagStyle,
            tagAlignment = tagAlignment,
            disabled = disabled
        )

        if (isTagAlignedEndWithSelectable && tagStyle != null) {
            OceanTag(
                style = tagStyle
            )
        }

        TrailingContentCardListItem(
            type = type,
            isSelected = isSelected,
            disabled = disabled,
            showChevron = showChevron
        )
    }
}

@Composable
private fun CenterContent(
    modifier: Modifier,
    title: String,
    description: String = "",
    caption: String,
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    tagStyle: OceanTagStyle?,
    tagAlignment: OceanCardListItemTagAlignment = OceanCardListItemTagAlignment.START,
    disabled: Boolean
) {
    Column(
        modifier = modifier
    ) {
        when (tagAlignment) {
            OceanCardListItemTagAlignment.START -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = OceanSpacing.xxxs)
                ) {
                    TitleWithTag(
                        title = title,
                        contentStyle = contentStyle,
                        tagStyle = tagStyle,
                        disabled = disabled
                    )
                }
            }
            OceanCardListItemTagAlignment.END -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TitleWithTag(
                        title = title,
                        contentStyle = contentStyle,
                        tagStyle = tagStyle,
                        disabled = disabled
                    )
                }
            }
        }

        if (description.isNotBlank()) {
            OceanText(
                text = description,
                style = contentStyle.getDescriptionStyle(),
                color = if (disabled) OceanColors.interfaceDarkUp else Color.Unspecified
            )
        }

        if (caption.isNotBlank()) {
            OceanSpacing.StackXXXS()

            OceanText(
                text = caption,
                style = OceanTextStyle.captionBold,
                color = if (disabled) OceanColors.interfaceDarkUp else Color.Unspecified
            )
        }
    }
}

@Composable
private fun TitleWithTag(
    title: String,
    contentStyle: OceanCardListItemContentStyle,
    tagStyle: OceanTagStyle?,
    disabled: Boolean
) {
    OceanText(
        text = title,
        style = contentStyle.getTitleStyle(),
        color = if (disabled) OceanColors.interfaceDarkUp else OceanColors.interfaceDarkPure
    )

    if (tagStyle != null) {
        OceanTag(
            style = tagStyle
        )
    }
}

@Composable
private fun LeadingContentCardListItem(
    type: OceanCardListItemType,
    style: OceanCardListItemStyle,
    isSelected: Boolean,
    disabled: Boolean
) {
    when (type) {
        is OceanCardListItemType.Default -> {
            LeadingDefaultTypeCardListItem(
                type = type,
                style = style,
                isSelected = isSelected,
                disabled = disabled
            )
        }

        is OceanCardListItemType.Selectable -> Unit
    }
}

@Composable
private fun TrailingContentCardListItem(
    type: OceanCardListItemType,
    isSelected: Boolean,
    disabled: Boolean,
    showChevron: Boolean = false
) {
    when (type) {
        is OceanCardListItemType.Default -> {
            Row {
                type.trailingIconToken?.let {
                    OceanSpacing.StackXS()

                    OceanIcon(
                        iconType = it,
                        modifier = Modifier.size(20.dp),
                        tint = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkUp
                    )
                }

                if (showChevron && type.trailingIconToken == null) {
                    OceanSpacing.StackXS()

                    OceanIcon(
                        iconType = OceanIcons.CHEVRON_RIGHT_SOLID,
                        modifier = Modifier.size(20.dp),
                        tint = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkUp
                    )
                }
            }
        }

        is OceanCardListItemType.Selectable -> {
            OceanSpacing.StackXXS()

            TrailingSelectableCardListItem(
                type = type,
                isSelected = isSelected,
                disabled = disabled
            )
        }
    }
}

@Preview
@Composable
fun OceanCardListItemPreview() {
    val context = LocalContext.current
    OceanTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xs)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            OceanCardListItem(
                title = "Title"
            )

            OceanCardListItem(
                title = "Title with Tag at Start (default)",
                description = "Description",
                tagStyle = OceanTagStyle.Default(
                    label = "Tag",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                onClick = null
            )

            OceanCardListItem(
                title = "Title with Tag at End",
                description = "Description",
                tagStyle = OceanTagStyle.Default(
                    label = "Tag",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                tagAlignment = OceanCardListItemTagAlignment.END
            )

            OceanCardListItem(
                title = "Title with Chevron",
                description = "Description with chevron icon",
                showChevron = true,
                onClick = {
                    Toast.makeText(context, "CardListItem clicked", Toast.LENGTH_SHORT).show()
                }
            )

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                onClick = {},
                disabled = true
            )

            OceanCardListItem(
                title = "Title Highlighted",
                description = "Description unselected",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { }
                ),
                style = OceanCardListItemStyle.Highlighted(
                    caption = "Short Caption",
                    backgroundColor = OceanColors.statusNegativeUp,
                    icon = OceanIcons.SPARKLES_ALT_SOLID,
                    iconColor = OceanColors.statusNegativeDeep,
                    animation = OceanCardListItemStyle.Highlighted.Animation(
                        targetBorderColor = OceanColors.statusNegativeDeep,
                        shadowColor = OceanColors.statusNegativeDeep
                    )
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Tag label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                onClick = {}
            )

            OceanCardListItem(
                title = "Highlighted with Tag at End",
                description = "Tag alinhada ao final",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { }
                ),
                style = OceanCardListItemStyle.Highlighted(
                    caption = "Short Caption",
                    backgroundColor = OceanColors.statusPositiveUp,
                    icon = OceanIcons.SPARKLES_OUTLINE,
                    iconColor = OceanColors.statusPositiveDeep
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Tag",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                tagAlignment = OceanCardListItemTagAlignment.END,
                onClick = {}
            )

            OceanCardListItem(
                title = "Title 2",
                description = "2",
                type = OceanCardListItemType.Default(
                    leadingIconToken = OceanIcons.PAGBLU_OUTLINE
                )
            ) { }

            var checkboxUnselected by remember { mutableStateOf(false) }
            OceanCardListItem(
                title = "Title Checkbox unselected",
                caption = "asdfasdf",
                description = "checkbox",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Checkbox,
                    didUpdate = { checkboxUnselected = it }
                ),
                isSelected = checkboxUnselected
            ) { checkboxUnselected = !checkboxUnselected }

            var radioSelected by remember { mutableStateOf(true) }
            OceanCardListItem(
                title = "Title Radio selected",
                description = "Radio",
                caption = "Selectable",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { radioSelected = it }
                ),
                isSelected = radioSelected
            ) { radioSelected = !radioSelected }

            var checkboxWithTag by remember { mutableStateOf(false) }
            OceanCardListItem(
                title = "Tag ao lado do Checkbox",
                description = "Tag alinhada END com tipo Selectable",
                tagStyle = OceanTagStyle.Default(
                    label = "Tag",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                tagAlignment = OceanCardListItemTagAlignment.END,
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Checkbox,
                    didUpdate = { checkboxWithTag = it }
                ),
                isSelected = checkboxWithTag,
                onClick = { checkboxWithTag = !checkboxWithTag }
            )

            var radioWithTag by remember { mutableStateOf(true) }
            OceanCardListItem(
                title = "Tag ao lado do Radio",
                description = "Tag alinhada END com radiobutton",
                tagStyle = OceanTagStyle.Default(
                    label = "Tag",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Warning
                ),
                tagAlignment = OceanCardListItemTagAlignment.END,
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { radioWithTag = it }
                ),
                isSelected = radioWithTag,
                onClick = { radioWithTag = !radioWithTag }
            )
        }
    }
}
