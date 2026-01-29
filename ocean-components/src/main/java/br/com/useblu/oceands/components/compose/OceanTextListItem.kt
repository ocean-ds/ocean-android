package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.components.compose.input.OceanSelectableRadio
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.OceanTextListContentStyle
import br.com.useblu.oceands.model.OceanTextListStyle
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderRadius
import br.com.useblu.oceands.utils.OceanIcons
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun OceanTextListItem(
    modifier: Modifier = Modifier,
    contentHorizontalPadding: Dp = OceanSpacing.xs,
    title: String,
    description: String = "",
    caption: String = "",
    captionStyle: TextStyle = OceanTextStyle.caption.copy(color = OceanColors.interfaceDarkUp),
    textInfo: String = "",
    textInfoColor: Color? = null,
    selected: Boolean = false,
    showDivider: Boolean = true,
    tagStyle: OceanTagStyle = OceanTagStyle.None,
    textListStyle: OceanTextListStyle = OceanTextListStyle.Default,
    contentStyle: OceanTextListContentStyle = OceanTextListContentStyle.Default,
    backgroundColor: Color = OceanColors.interfaceLightPure,
    showError: Boolean = false,
    enabled: Boolean = true,
    onSelectedBox: ((Boolean) -> Unit)? = null,
    showClickableChevron: Boolean = true,
    onClick: ((isEnabled: Boolean) -> Unit)? = null,
    onDisabledClickAvailable: Boolean = false
) {
    val isNotReadOnly = (
        onClick != null &&
            showClickableChevron
        ) ||
        (
            textListStyle != OceanTextListStyle.RadioButton ||
                textListStyle != OceanTextListStyle.Checkbox
            )

    Column(
        modifier = modifier
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(vertical = if (isNotReadOnly) OceanSpacing.xs else OceanSpacing.xxs)
                .padding(horizontal = contentHorizontalPadding)
                .fillMaxWidth()
                .clickable(
                    interactionSource = null,
                    indication = null,
                    enabled = enabled || onDisabledClickAvailable,
                    onClick = {
                        onClick?.invoke(enabled)
                    }
                )
        ) {
            when (textListStyle) {
                OceanTextListStyle.Checkbox -> Unit
                OceanTextListStyle.RadioButton -> Unit
                OceanTextListStyle.Default -> Unit
                is OceanTextListStyle.Icon -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = OceanSpacing.xs)
                    ) {
                        OceanIcon(
                            iconType = textListStyle.icon ?: OceanIcons.INFO_OUTLINE
                        )
                    }
                }

                is OceanTextListStyle.Image -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = OceanSpacing.xs)
                    ) {
                        GlideImage(
                            imageModel = { textListStyle.imageUrl },
                            modifier = Modifier
                                .heightIn(min = 40.dp, max = 60.dp)
                                .widthIn(min = 40.dp, max = 60.dp),
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(
                        end = if (isNotReadOnly) OceanSpacing.xxs
                        else OceanSpacing.xs
                    )
                    .weight(2f)
            ) {
                OceanText(
                    text = title,
                    style = contentStyle.titleTextStyle(),
                    color = contentStyle.titleColor(enabled)
                )

                if (description.isNotBlank()) {
                    OceanText(
                        modifier = Modifier.padding(bottom = OceanSpacing.xxxs),
                        text = description,
                        style = contentStyle.descriptionTextStyle(),
                        color = contentStyle.descriptionColor(enabled)
                    )
                }

                if (caption.isNotBlank() && textInfo.isBlank()) {
                    OceanText(
                        text = caption,
                        style = captionStyle
                    )
                }

                if (textInfo.isNotBlank()) {
                    OceanText(
                        text = textInfo,
                        style = OceanTextStyle.description,
                        color = textInfoColor ?: OceanColors.interfaceDarkDeep
                    )
                }
            }
            if (tagStyle != OceanTagStyle.None) {
                OceanTag(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = tagStyle
                )
            }
            when (textListStyle) {
                OceanTextListStyle.Checkbox -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = OceanSpacing.xs)
                    ) {
                        OceanSelectableBox(
                            interactionSource = interactionSource,
                            enabled = enabled,
                            selected = selected,
                            showError = showError,
                            onSelectedBox = onSelectedBox
                        )
                    }
                }

                OceanTextListStyle.RadioButton -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = OceanSpacing.xs)
                    ) {
                        OceanSelectableRadio(
                            interactionSource = interactionSource,
                            enabled = enabled,
                            isSelected = selected,
                            showError = showError,
                            onSelectedBox = {
                                onSelectedBox?.invoke(true)
                            }
                        )
                    }
                }

                else -> Unit
            }
            if (onClick != null && showClickableChevron) {
                Column(
                    Modifier
                        .align(Alignment.CenterVertically)
                        .offset(x = OceanSpacing.xxs)
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = OceanIcons.CHEVRON_RIGHT_SOLID.icon),
                        colorFilter = ColorFilter.tint(OceanColors.interfaceDarkUp),
                        contentDescription = ""
                    )
                }
            }
        }

        if (textListStyle != OceanTextListStyle.Default && showDivider) {
            OceanDivider(
                modifier = Modifier.padding(horizontal = contentHorizontalPadding)
            )
        }
    }
}

@Composable
fun OceanTextListItemSkeleton(items: Int) {
    OceanShimmering { brush ->
        repeat(items) {
            Column(
                modifier = Modifier
                    .background(OceanColors.interfaceLightPure)
                    .padding(OceanSpacing.xs)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(bottom = OceanSpacing.xxs)
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(96.dp)
                            .height(16.dp)
                            .borderRadius(borderRadius = OceanBorderRadius.Tiny.allCorners)
                            .background(brush)
                    )
                }
                Row {
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxSize()
                            .borderRadius(borderRadius = OceanBorderRadius.Tiny.allCorners)
                            .background(brush)
                    )
                }
            }
        }
    }
}

@Preview(heightDp = 1200)
@Composable
fun OceanTextListItemPreview() {
    Column(
        Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
            .background(color = OceanColors.interfaceLightPure)
    ) {
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "<span style=\"color:#2DA94F\">Em análise</ span>",
            selected = false,
            showError = false,
            enabled = true,
            onClick = {},
            showClickableChevron = false
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            caption = "Caption",
            showClickableChevron = true,
            selected = false,
            showError = false,
            textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BRAND_MASTERCARD),
            tagStyle = OceanTagStyle.Default(
                label = "Label",
                layout = OceanTagLayout.Medium(),
                type = OceanTagType.Positive
            ),
            enabled = true,
            onClick = {}
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title - Blank Icon",
            description = "Description",
            textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BLANK_ICON),
            selected = false,
            showError = false,
            enabled = false
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            caption = "Caption",
            selected = false,
            showError = false,
            enabled = true,
            onClick = {
                println("text list clicked")
            }
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            caption = "Caption",
            textInfo = "TextInfo",
            textInfoColor = OceanColors.statusPositivePure,
            showError = false,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title Inverted",
            description = "Description Inverted",
            selected = false,
            textListStyle = OceanTextListStyle.Checkbox,
            contentStyle = OceanTextListContentStyle.Inverted,
            showError = false,
            enabled = true,
            onSelectedBox = {
                println("isSelected: $it")
            }
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = false,
            textListStyle = OceanTextListStyle.Checkbox,
            showError = true,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.Checkbox,
            showError = false,
            enabled = true,
            onSelectedBox = {
                println("isSelected: $it")
            }
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.Checkbox,
            showError = false,
            enabled = false
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = false,
            textListStyle = OceanTextListStyle.RadioButton,
            showError = false,
            enabled = true,
            onSelectedBox = {
                println("isSelected: $it")
            }
        )
        OceanTextListItem(
            modifier = Modifier,
            contentHorizontalPadding = 0.dp,
            title = "Title",
            description = "Description",
            selected = false,
            textListStyle = OceanTextListStyle.RadioButton,
            showError = true,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title Sem divider",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.RadioButton,
            showError = false,
            enabled = true,
            showDivider = false,
            onSelectedBox = {
                println("isSelected: $it")
            }
        )
        OceanTextListItem(
            title = "Title",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.RadioButton,
            showError = false,
            enabled = false,
            onClick = {
                println("onDisableClick called: $it")
            },
            onDisabledClickAvailable = true
        )
        OceanTextListItemSkeleton(5)
    }
}
