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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.components.compose.input.OceanSelectableRadio
import br.com.useblu.oceands.model.OceanTextListStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
private fun OceanTextListItemPreview() {
    Column(
        Modifier.verticalScroll(
            state = rememberScrollState(),
            enabled = true
        )
        .background(color = OceanColors.interfaceLightPure)
    ) {
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
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
            title = "Title",
            description = "Description",
            selected = false,
            textListStyle = OceanTextListStyle.WITH_CHECKBOX,
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
            textListStyle = OceanTextListStyle.WITH_CHECKBOX,
            showError = true,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.WITH_CHECKBOX,
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
            textListStyle = OceanTextListStyle.WITH_CHECKBOX,
            showError = false,
            enabled = false
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = false,
            textListStyle = OceanTextListStyle.WITH_RADIO_BUTTON,
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
            textListStyle = OceanTextListStyle.WITH_RADIO_BUTTON,
            showError = true,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.WITH_RADIO_BUTTON,
            showError = false,
            enabled = true,
            onSelectedBox = {
                println("isSelected: $it")
            }
        )
        OceanTextListItem(
            title = "Title",
            description = "Description",
            selected = true,
            textListStyle = OceanTextListStyle.WITH_RADIO_BUTTON,
            showError = false,
            enabled = false
        )
        OceanTextListItemSkeleton(5)
    }
}


@Composable
fun OceanTextListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    caption: String = "",
    textInfo: String = "",
    textInfoColor: Color? = null,
    selected: Boolean = false,
    textListStyle: OceanTextListStyle = OceanTextListStyle.DEFAULT,
    showError: Boolean = false,
    enabled: Boolean = true,
    onSelectedBox: ((Boolean) -> Unit)? = null,
    showClickableChevron: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        Row(
            modifier = Modifier
                .background(OceanColors.interfaceLightPure)
                .padding(start = OceanSpacing.xs)
                .padding(end = OceanSpacing.xxs)
                .padding(vertical = OceanSpacing.xxsExtra)
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled,
                    onClick = {
                        onClick?.invoke()
                    }
                )
        ) {

            when (textListStyle) {
                OceanTextListStyle.WITH_CHECKBOX -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = OceanSpacing.xs)
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
                OceanTextListStyle.WITH_RADIO_BUTTON -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = OceanSpacing.xs)
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
                OceanTextListStyle.DEFAULT -> Unit
            }

            Column(
                modifier = Modifier
                    .padding(
                        end = if(onClick != null && showClickableChevron) OceanSpacing.xxsExtra
                              else OceanSpacing.xxs
                    )
                    .weight(2f)
            ) {
                val titleColor =
                    if (enabled) OceanColors.interfaceDarkPure
                    else OceanColors.interfaceDarkUp

                Text(
                    text = title,
                    style = OceanTextStyle.paragraph,
                    color = titleColor
                )

                val descriptionColor =
                    if (enabled) OceanColors.interfaceDarkDown
                    else OceanColors.interfaceLightDeep

                Text(
                    modifier = Modifier.padding(bottom = OceanSpacing.xxxs),
                    text = description,
                    style = OceanTextStyle.description,
                    color = descriptionColor
                )

                if (caption.isNotBlank() && textInfo.isBlank()) {
                    Text(
                        text = caption,
                        style = OceanTextStyle.caption,
                        color = OceanColors.interfaceDarkUp
                    )
                }

                if (textInfo.isNotBlank()) {
                    Text(
                        text = textInfo,
                        style = OceanTextStyle.description,
                        color = textInfoColor ?: OceanColors.interfaceDarkDeep
                    )
                }
            }
            if (onClick != null && showClickableChevron) {
                Column(
                    Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = OceanIcons.CHEVRON_RIGHT_OUTLINE.icon),
                        contentDescription = ""
                    )
                }
            }
        }

        if(textListStyle != OceanTextListStyle.DEFAULT){
            HorizontalDivider(
                thickness = 1.dp,
                color = OceanColors.interfaceLightDown
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
                            .clip(RoundedCornerShape(4.dp))
                            .background(brush)
                    )
                }
                Row {
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                            .background(brush)
                    )
                }
            }
        }
    }
}
