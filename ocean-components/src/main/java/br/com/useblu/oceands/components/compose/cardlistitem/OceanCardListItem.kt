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
import androidx.compose.material3.Text
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

@Composable
fun OceanCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    style: OceanCardListItemStyle = OceanCardListItemStyle.Default,
    disabled: Boolean = false,
    isSelected: Boolean = false,
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
                type = type,
                disabled = disabled,
                isSelected = isSelected,
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
                type = type,
                style = style,
                disabled = disabled,
                isSelected = isSelected,
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
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    type: OceanCardListItemType,
    style: OceanCardListItemStyle,
    isSelected: Boolean,
    disabled: Boolean
) {
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
            tagStyle = tagStyle,
            disabled = disabled
        )

        TrailingContentCardListItem(
            type = type,
            isSelected = isSelected,
            disabled = disabled
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
    disabled: Boolean
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = OceanSpacing.xxxs)
        ) {
            Text(
                text = title,
                style = contentStyle.getTitleStyle(),
                color = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkPure
            )

            if (tagStyle != null) {
                OceanTag(
                    style = tagStyle
                )
            }
        }

        if (description.isNotBlank()) {
            OceanText(
                text = description,
                style = contentStyle.getDescriptionStyle(),
                color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
            )
        }

        if (caption.isNotBlank()) {
            OceanSpacing.StackXXXS()

            OceanText(
                text = caption,
                style = OceanTextStyle.caption,
                color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
            )
        }
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
    disabled: Boolean
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
            }
        }

        is OceanCardListItemType.Selectable -> {
            OceanSpacing.StackXS()

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
                title = "Title",
                onClick = {}
            )

            OceanCardListItem(
                title = "Title",
                description = "Description",
                onClick = {}
            )

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                onClick = {},
                disabled = true,
                onDisabledClick = {
                    Toast.makeText(context, "Item disabled", Toast.LENGTH_SHORT).show()
                }
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
                title = "Title Highlighted",
                description = "Without Animation",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { }
                ),
                style = OceanCardListItemStyle.Highlighted(
                    caption = "Short Caption",
                    backgroundColor = OceanColors.statusNegativeUp,
                    icon = OceanIcons.SPARKLES_OUTLINE,
                    iconColor = OceanColors.statusNegativeDeep
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Tag label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                onClick = {}
            )

            OceanCardListItem(
                title = "Title Highlighted",
                description = "Without Animation",
                disabled = true,
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { }
                ),
                style = OceanCardListItemStyle.Highlighted(
                    caption = "Short Caption",
                    backgroundColor = OceanColors.statusNegativeUp,
                    icon = OceanIcons.SPARKLES_OUTLINE,
                    iconColor = OceanColors.statusNegativeDeep
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Tag label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                onClick = {},
                onDisabledClick = {
                    Toast.makeText(context, "Item disabled", Toast.LENGTH_SHORT).show()
                }
            )

            OceanCardListItem(
                title = "Title Highlighted Selected",
                description = "Description selected",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { }
                ),
                style = OceanCardListItemStyle.Highlighted(
                    caption = "Receba no mesmo dia em que seu cliente pagar, com um menor custo de antecipação.",
                    backgroundColor = OceanColors.statusPositiveUp,
                    icon = OceanIcons.SPARKLES_ALT_SOLID,
                    iconColor = OceanColors.statusPositiveDeep,
                    animation = OceanCardListItemStyle.Highlighted.Animation(
                        targetBorderColor = OceanColors.statusPositiveDeep,
                        shadowColor = OceanColors.statusPositiveDeep
                    )
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Mais vendido",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                ),
                isSelected = true,
                onClick = {}
            )

            OceanCardListItem(
                title = "Title 2",
                description = "2",
                type = OceanCardListItemType.Default(
                    leadingIconToken = OceanIcons.PAGBLU_OUTLINE
                )
            ) { }

            OceanCardListItem(
                title = "Title Selected",
                description = "Selected",
                type = OceanCardListItemType.Default(
                    leadingIconToken = OceanIcons.PAGBLU_OUTLINE
                ),
                isSelected = true
            ) { }

            OceanCardListItem(
                title = "Title Disabled",
                description = "Disabled",
                caption = "Caption",
                type = OceanCardListItemType.Default(
                    leadingIconToken = OceanIcons.PAGBLU_OUTLINE
                ),
                disabled = true
            ) { }

            var checkboxUnselected by remember { mutableStateOf(false) }
            OceanCardListItem(
                title = "Title Checkbox unselected",
                description = "checkbox",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Checkbox,
                    didUpdate = { checkboxUnselected = it }
                ),
                isSelected = checkboxUnselected
            ) { checkboxUnselected = !checkboxUnselected }

            var checkboxSelected by remember { mutableStateOf(true) }
            OceanCardListItem(
                title = "Title Checkbox selected",
                description = "checkbox",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Checkbox,
                    didUpdate = { checkboxSelected = it }
                ),
                isSelected = checkboxSelected
            ) { checkboxSelected = !checkboxSelected }

            var radioUnselected by remember { mutableStateOf(false) }
            OceanCardListItem(
                title = "Title Radio unselected",
                description = "Radio",
                caption = "Selectable",
                type = OceanCardListItemType.Selectable(
                    selectionType = OceanCardListItemType.Selectable.SelectionType.Radiobutton,
                    didUpdate = { radioUnselected = it }
                ),
                isSelected = radioUnselected
            ) { radioUnselected = !radioUnselected }

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
        }
    }
}
