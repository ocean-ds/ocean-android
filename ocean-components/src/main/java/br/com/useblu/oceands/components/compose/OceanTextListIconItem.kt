package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanIconType
import br.com.useblu.oceands.model.compose.OceanTextListIconItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle


@Preview(device = "spec:width=800dp,height=550.9dp,dpi=440")
@Composable
private fun OceanTextListIconItemPreview() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanTextListIconItem(
                title = "Title"
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description"
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                badgeText = "Teste"
            )
        }

        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanTextListIconItem(
                title = "Title",
                leadingIconToken = OceanIconType.SWITCH_HORIZONTAL_OUTLINE
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description",
                leadingIconToken = OceanIconType.PLACEHOLDER_OUTLINE
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIconType.PLACEHOLDER_OUTLINE
            )
        }

        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanTextListIconItem(
                title = "Title",
                leadingIconToken = OceanIconType.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIconType.PLACEHOLDER_OUTLINE,
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description",
                leadingIconToken = OceanIconType.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIconType.PLACEHOLDER_OUTLINE,
            )

            OceanSpacing.StackXXS()

            OceanTextListIconItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIconType.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIconType.PLACEHOLDER_OUTLINE,
            )
        }
    }
}

@Preview
@Composable
private fun OceanTextListIconPreview() {
    val models = listOf(
        OceanTextListIconItemModel(
            title = "Transferir",
            leadingIconToken = OceanIconType.SWITCH_HORIZONTAL_OUTLINE,
        ),
        OceanTextListIconItemModel(
            title = "Transferir",
            leadingIconToken = OceanIconType.DUPLICATE_OUTLINE,
        ),
        OceanTextListIconItemModel(
            title = "Transferir",
            leadingIconToken = OceanIconType.SCALE_OUTLINE
        ) {
            println("Click")
        }
    )
    MaterialTheme {
        OceanTextListIcon(models = models)
    }
}

@Composable
fun OceanTextListIcon(
    models: List<OceanTextListIconItemModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(models) {index, item ->
            OceanTextListIconItem(item)

            if (index < models.size - 1) {
                Divider(color = OceanColors.interfaceLightDown)
            }
        }
    }
}

@Composable
fun OceanTextListIconItem(
    model: OceanTextListIconItemModel
) {
    OceanTextListIconItem(
        title = model.title,
        modifier = model.modifier,
        description = model.description,
        caption = model.caption,
        badgeText = model.badge,
        leadingIconToken = model.leadingIconToken,
        trailingIconToken = model.trailingIconToken,
        onClick = model.onClick
    )
}

@Composable
fun OceanTextListIconItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    caption: String? = null,
    badgeText: String? = null,
    badgeType: OceanBadgeType = OceanBadgeType.HIGHLIGHT,
    leadingIconToken: OceanIconType? = null,
    trailingIconToken: OceanIconType? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clickable(
                enabled = onClick != null,
                onClick = { onClick?.invoke() }
            )
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        leadingIconToken?.let {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = OceanColors.interfaceLightUp,
                        shape = CircleShape
                    )
            ) {
                OceanIcon(
                    iconType = it,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                    tint = OceanColors.brandPrimaryDown
                )
            }

            OceanSpacing.StackXS()
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = OceanTextStyle.paragraph,
                    color = OceanColors.interfaceDarkPure
                )

                badgeText?.let {
                    OceanSpacing.StackXXS()

                    OceanBadge(
                        text = it,
                        type = badgeType,
                        size = OceanBadgeSize.Small
                    )
                }
            }

            description?.let {
                Text(
                    text = description,
                    style = OceanTextStyle.description
                )
            }

            caption?.let {
                OceanSpacing.StackXXXS()

                Text(
                    text = caption,
                    style = OceanTextStyle.caption
                )
            }
        }

        trailingIconToken?.let {
            OceanSpacing.StackXS()

            OceanIcon(
                iconType = it,
                modifier = Modifier.size(20.dp),
                tint = OceanColors.interfaceDarkUp
            )
        }
    }
}


