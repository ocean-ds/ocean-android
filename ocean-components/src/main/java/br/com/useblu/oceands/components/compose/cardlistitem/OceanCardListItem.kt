package br.com.useblu.oceands.components.compose.cardlistitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.components.compose.input.OceanSelectableRadio
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
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
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    style: OceanCardListItemStyle = OceanCardListItemStyle.Default,
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    when (style) {
        OceanCardListItemStyle.Default -> {
            DefaultCardListItem(
                modifier = modifier,
                title = title,
                description = description,
                caption = caption,
                tagStyle = tagStyle,
                type = type,
                disabled = disabled,
                isSelected = isSelected,
                onClick = onClick
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
private fun ContentCardListItem(
    title: String,
    description: String,
    caption: String,
    tagStyle: OceanTagStyle?,
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
            tagStyle = tagStyle,
            disabled = disabled
        )

        TrailingContentCardListItem(
            type = type,
            disabled = disabled
        )
    }
}

@Composable
private fun CenterContent(
    modifier: Modifier,
    title: String,
    description: String,
    caption: String,
    tagStyle: OceanTagStyle?,
    disabled: Boolean
) {
    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                text = title,
                style = OceanTextStyle.paragraph,
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
                style = OceanTextStyle.description,
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
            if (type.leadingIconToken != null) {
                val iconSize = if (type.showIconBackground) 24.dp else 20.dp
                val iconBackgroundColor = style.getIconBackgroundColor(isSelected = isSelected)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .iconContainerBackground(type.showIconBackground, iconBackgroundColor)
                ) {
                    OceanIcon(
                        iconType = type.leadingIconToken,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(iconSize),
                        tint = when {
                            disabled -> OceanColors.interfaceLightDeep
                            isSelected -> OceanColors.interfaceLightPure
                            else -> OceanColors.brandPrimaryDown
                        }
                    )
                }

                OceanSpacing.StackXS()
            }
        }

        is OceanCardListItemType.Selectable -> {
            Column(
                modifier = Modifier
                    .padding(end = OceanSpacing.xs),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (type.selectionType) {
                    OceanCardListItemType.Selectable.SelectionType.Checkbox -> {
                        OceanSelectableBox(
                            selected = isSelected,
                            enabled = !disabled,
                            onSelectedBox = type.didUpdate
                        )
                    }
                    OceanCardListItemType.Selectable.SelectionType.Radiobutton -> {
                        OceanSelectableRadio(
                            isSelected = isSelected,
                            enabled = !disabled,
                            onSelectedBox = { type.didUpdate(!isSelected) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrailingContentCardListItem(
    type: OceanCardListItemType,
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
            Unit
        }
    }
}

@Composable
private fun DefaultCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val backgroundColor = OceanCardListItemStyle.Default.getBackgroundColor(
        isSelected = isSelected,
        type = type
    )
    val borderColor = OceanCardListItemStyle.Default.getBorderColor(
        isSelected = isSelected,
        type = type
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        shape = OceanBorderRadius.SM.allCorners.shape(),
        border = BorderStroke(1.dp, borderColor),
        enabled = onClick != null && !disabled,
        onClick = { onClick?.invoke() }
    ) {
        ContentCardListItem(
            title = title,
            description = description,
            caption = caption,
            tagStyle = tagStyle,
            type = type,
            style = OceanCardListItemStyle.Default,
            isSelected = isSelected,
            disabled = disabled
        )
    }
}

@Preview
@Composable
fun OceanCardListItemPreview() {
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
