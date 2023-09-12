package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanTextListItemPreview() {
    Column(
        Modifier.verticalScroll(
            state = rememberScrollState(),
            enabled = true
        )
    ) {
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = false,
            showError = false,
            enabled = true
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
            withCheckbox = true,
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
            withCheckbox = true,
            showError = true,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = true,
            withCheckbox = true,
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
            withCheckbox = true,
            showError = false,
            enabled = false
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = false,
            withCheckbox = false,
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
            withCheckbox = false,
            showError = true,
            enabled = true
        )
        OceanTextListItem(
            modifier = Modifier,
            title = "Title",
            description = "Description",
            selected = true,
            withCheckbox = false,
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
            withCheckbox = false,
            showError = false,
            enabled = false
        )
        OceanTextListItemSkeleton(5)
    }
}


@Composable
fun OceanTextListItem(
    modifier: Modifier,
    title: String,
    description: String,
    caption: String = "",
    textInfo: String = "",
    textInfoColor: Color? = null,
    selected: Boolean = false,
    withCheckbox: Boolean? = null,
    showError: Boolean,
    enabled: Boolean,
    onSelectedBox: ((Boolean?) -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Column {
        Row(modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .padding(start = 16.dp)
            .padding(end = 8.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
        ) {
            if (withCheckbox != null) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                ) {
                    if (withCheckbox) {
                        OceanSelectableBox(
                            enabled = enabled,
                            selected = selected,
                            showError = showError,
                            onSelectedBox = onSelectedBox
                        )
                    } else {
                        OceanSelectableRadio(
                            enabled = enabled,
                            selected = selected,
                            showError = showError,
                            onSelectedBox = onSelectedBox
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(2f)
                    .clickable { onClick?.invoke() }
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
                    modifier = modifier.padding(bottom = 4.dp),
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
            if (onClick != null) {
                Column(
                    Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Image(
                        modifier = modifier.size(20.dp),
                        painter = painterResource(id = OceanIcons.CHEVRON_RIGHT_OUTLINE.icon),
                        contentDescription = ""
                    )
                }
            }
        }
        Divider(
            thickness = 1.dp,
            color = OceanColors.interfaceLightDown
        )
    }
}

@Composable
fun OceanTextListItemSkeleton(items: Int) {
    OceanShimmering { brush ->
        repeat(items) {
            Column(
                modifier = Modifier
                    .background(OceanColors.interfaceLightPure)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp)
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
