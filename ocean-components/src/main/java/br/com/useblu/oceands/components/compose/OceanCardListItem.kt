package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle


@Preview(device = "spec:width=800dp,height=550.9dp,dpi=440")
@Composable
private fun OceanCardListItemPreview() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanCardListItem(
                title = "Title"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        }

        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanCardListItem(
                title = "Title",
                leadingIconToken = "switchhorizontaloutline"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                leadingIconToken = "placeholderoutline"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = "placeholderoutline"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = "placeholderoutline",
                disabled = true
            )
        }

        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanCardListItem(
                title = "Title",
                leadingIconToken = "placeholderoutline",
                trailingIconToken = "placeholderoutline",
                showIconBackground = false
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                leadingIconToken = "placeholderoutline",
                trailingIconToken = "placeholderoutline",
                showIconBackground = false
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = "placeholderoutline",
                trailingIconToken = "placeholderoutline",
                showIconBackground = false
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = "placeholderoutline",
                trailingIconToken = "placeholderoutline",
                disabled = true
            )
        }
    }
}

@Composable
fun OceanCardListItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    caption: String? = null,
    leadingIconToken: String? = null,
    trailingIconToken: String? = null,
    showIconBackground: Boolean = true,
    disabled: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clickable(
                enabled = onClick != null && !disabled,
                onClick = { onClick?.invoke() }
            )
            .background(
                color = OceanColors.interfaceLightPure,
            )
            .border(
                width = 1.dp,
                color = OceanColors.interfaceLightDown,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIconToken?.let {
            val iconSize = if (showIconBackground) 24.dp else 20.dp

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .iconContainerBackground(showIconBackground)
            ) {
                OceanIcon(
                    token = it,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(iconSize),
                    tint = if (disabled) OceanColors.interfaceLightDeep else OceanColors.brandPrimaryDown
                )
            }

            OceanSpacing.StackXS()
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = OceanTextStyle.paragraph,
                color = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkPure
            )

            description?.let {
                Text(
                    text = description,
                    style = OceanTextStyle.description,
                    color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
                )
            }

            caption?.let {
                OceanSpacing.StackXXXS()

                Text(
                    text = caption,
                    style = OceanTextStyle.caption,
                    color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
                )
            }
        }

        trailingIconToken?.let {
            OceanSpacing.StackXS()

            OceanIcon(
                token = it,
                modifier = Modifier.size(20.dp),
                tint = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkUp
            )
        }
    }
}


